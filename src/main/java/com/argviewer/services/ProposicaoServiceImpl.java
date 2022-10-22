package com.argviewer.services;

import com.argviewer.domain.interfaces.mappers.ProposicaoMapper;
import com.argviewer.domain.interfaces.repositories.ProposicaoRepository;
import com.argviewer.domain.interfaces.repositories.UsuarioRepository;
import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.entities.Proposicao;
import com.argviewer.domain.model.entities.Usuario;
import com.argviewer.domain.model.exceptions.EntityNotFoundException;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ProposicaoServiceImpl implements ProposicaoService {

    private final ProposicaoRepository proposicaoRepository;

    private final UsuarioRepository usuarioRepository;

    private final ProposicaoMapper proposicaoMapper;

    public ProposicaoServiceImpl(ProposicaoRepository proposicaoRepository, UsuarioRepository usuarioRepository, ProposicaoMapper proposicaoMapper) {
        this.proposicaoRepository = proposicaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.proposicaoMapper = proposicaoMapper;
    }

    static Specification<Proposicao> belongsTo(int usuarioId) {
        return (proposicao, cq, cb) -> cb.equal(proposicao.get("usuario").get("id"), usuarioId);
    }

    static Specification<Proposicao> containsTag(int tagId) {
        return (proposicao, cq, cb) -> proposicao.join("tags").get("id").in(Set.of(tagId));
    }

    @Override
    public Set<ProposicaoDTO> find(Integer usuarioId, Integer tagId) {
        Set<Proposicao> proposicoes;

        if (usuarioId != null && tagId != null)
            proposicoes = Set.copyOf(proposicaoRepository.findAll(
                    where(belongsTo(usuarioId)).and(containsTag(tagId))));
        else if (usuarioId != null)
            proposicoes = Set.copyOf(proposicaoRepository.findAll(where(belongsTo(usuarioId))));
        else if (tagId != null)
            proposicoes = Set.copyOf(proposicaoRepository.findAll(containsTag(tagId)));
        else
            proposicoes = Set.copyOf(proposicaoRepository.findAll());

        return proposicaoMapper.proposicoesToDtoSet(proposicoes);
    }

    @Override
    public Optional<ProposicaoDTO> findById(int id) {
        Optional<Proposicao> proposicao = proposicaoRepository.findById(id);
        return proposicao.map(proposicaoMapper::proposicaoToDto);
    }

    static Specification<Proposicao> containsTexto(String texto) {
        return (proposicao, cq, cb) -> proposicao.get("texto").in(texto);
    }

    @Override
    public Set<ProposicaoDTO> findByTextoContaining(String value) {
        Set<Proposicao> proposicoes = Set.copyOf(proposicaoRepository.findAll(where(containsTexto(value))));
        return proposicaoMapper.proposicoesToDtoSet(proposicoes);
    }

    @Override
    public Set<ProposicaoDTO> findRespostas(int proposicaoId) {
        Proposicao proposicao = proposicaoRepository
                .findById(proposicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));
        return proposicaoMapper.proposicoesToDtoSet(proposicao.getRespostas());
    }

    @Override
    public int create(ProposicaoDTO dto) {
        Proposicao proposicao = proposicaoMapper.dtoToProposicao(dto);
        return proposicaoRepository.save(proposicao).getId();
    }

    @Override
    public void update(ProposicaoDTO dto) {
        Proposicao proposicao = proposicaoRepository
                .findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));
        proposicaoMapper.dtoToProposicao(dto, proposicao);
        proposicaoRepository.save(proposicao);
    }

    @Override
    public boolean saveRespostas(int proposicaoId, int replicaId) throws IllegalOperationException {
        if (proposicaoId == replicaId)
            throw new IllegalOperationException("A proposição não pode ser uma replica a ela mesma.");

        Proposicao proposicao = proposicaoRepository
                .findById(proposicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Proposicao replica = proposicaoRepository
                .findById(replicaId)
                .orElseThrow(() -> new EntityNotFoundException("Replica não encontrada."));

        if (proposicao.getRespostas().contains(replica)) {
            proposicao.getRespostas().remove(replica);
            proposicaoRepository.save(proposicao);
            return false;
        }

        List<Float> cosineScores = new ArrayList<>();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("sentence", replica.getTexto());
            map.put("sentences_to_compare", Stream.concat(
                    Stream.of(
                            proposicao.getTexto()),
                            proposicao.getRespostas().stream().map(Proposicao::getTexto))
                    .collect(Collectors.toList()));
            JSONObject body = new JSONObject(map);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .uri(URI.create("https://argviewer-sentence-analyzer.herokuapp.com/api/similarity"))
                    .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONArray json = new JSONArray(response.body());

            for (int i = 0; i < json.length(); i++) {
                cosineScores.add(new BigDecimal(json.get(i).toString()).floatValue());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (cosineScores.stream().anyMatch(score -> score > 0.90))
            throw new IllegalOperationException("Essa resposta é muito semelhante à proposição inicial.");

        proposicao.getRespostas().add(replica);
        proposicaoRepository.save(proposicao);
        return true;
    }

    @Override
    public void addSeguidor(int proposicaoId, int seguidorId) throws IllegalOperationException {
        Proposicao proposicao = proposicaoRepository
                .findById(proposicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Usuario seguidor = usuarioRepository
                .findById(seguidorId)
                .orElseThrow(() -> new EntityNotFoundException("Seguidor não encontrado."));

        if (proposicao.getSeguidores().contains(seguidor))
            throw new IllegalOperationException("Você já segue essa proposição.");

        proposicao.getSeguidores().add(seguidor);
        proposicaoRepository.save(proposicao);
    }

    @Override
    public void removeSeguidor(int proposicaoId, int seguidorId) throws IllegalOperationException {
        Proposicao proposicao = proposicaoRepository
                .findById(proposicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Usuario seguidor = usuarioRepository
                .findById(seguidorId)
                .orElseThrow(() -> new EntityNotFoundException("Seguidor não encontrada."));

        if (!proposicao.getSeguidores().contains(seguidor))
            throw new IllegalOperationException("Você não segue essa proposição.");

        proposicao.getSeguidores().remove(seguidor);
        proposicaoRepository.save(proposicao);
    }

    @Override
    public void deleteById(int proposicaoId) {
        proposicaoRepository.deleteById(proposicaoId);
    }
}
