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
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<ProposicaoDTO> findReplicas(int proposicaoId) {
        Proposicao proposicao = proposicaoRepository
                .findById(proposicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));
        return proposicaoMapper.proposicoesToDtoSet(proposicao.getReplicas());
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

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        return file.getAbsolutePath();
    }

    private String toQuote(String texto){
        return String.format("'%s'", texto);
    }

    @Override
    public boolean saveReplicas(int proposicaoId, int replicaId) throws IllegalOperationException {
        if (proposicaoId == replicaId)
            throw new IllegalOperationException("A proposição não pode ser uma replica a ela mesma.");

        Proposicao proposicao = proposicaoRepository
                .findById(proposicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Proposicao replica = proposicaoRepository
                .findById(replicaId)
                .orElseThrow(() -> new EntityNotFoundException("Replica não encontrada."));

        if (proposicao.getReplicas().contains(replica)) {
            proposicao.getReplicas().remove(replica);
            proposicaoRepository.save(proposicao);
            return false;
        }

        String line = String.format(
                "py %s '%s' '%s' %s",
                resolvePythonScriptPath("python/sentence_similarity.py"),
                replica.getTexto(),
                proposicao.getTexto(),
                proposicao.getReplicas().stream().map(p -> toQuote(p.getTexto())).collect(Collectors.joining(" "))
        );
        CommandLine cmdLine = CommandLine.parse(line);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);
        try {
            executor.execute(cmdLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Float> cosineScores = Arrays.stream(outputStream.toString().trim().split(", ")).map(Float::parseFloat).collect(Collectors.toList());

        if (cosineScores.stream().anyMatch(score -> score > 0.90))
            throw new IllegalOperationException("Essa replica é muito semelhante à proposição");

        proposicao.getReplicas().add(replica);
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
