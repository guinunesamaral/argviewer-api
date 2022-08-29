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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    static Specification<Proposicao> proposicaoContains(int usuarioId) {
        return (proposicao, cq, cb) -> cb.equal(proposicao.get("usuarioId"), usuarioId);
    }

    @Override
    public Set<ProposicaoDTO> find(Integer idUsuario) {
        Set<Proposicao> proposicaoSet;

        if (idUsuario != null)
            proposicaoSet = Set.copyOf(proposicaoRepository.findAll(Specification.where(proposicaoContains(idUsuario))));
        else
            proposicaoSet = Set.copyOf(proposicaoRepository.findAll());

        return proposicaoMapper.proposicoesToDtoSet(proposicaoSet);
    }

    @Override
    public Optional<ProposicaoDTO> findById(int id) {
        Optional<Proposicao> proposicao = proposicaoRepository.findById(id);
        return proposicao.map(proposicaoMapper::proposicaoToDto);
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
        proposicao.setDataAlteracao(LocalDateTime.now());
        proposicaoRepository.save(proposicao);
    }

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        return file.getAbsolutePath();
    }

    @Override
    public void addAnswer(int idProposicao, int idResposta) {
        Proposicao proposicao = proposicaoRepository
                .findById(idProposicao)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Proposicao resposta = proposicaoRepository
                .findById(idResposta)
                .orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada."));

        String line = String.format(
                "py %s %s %s",
                resolvePythonScriptPath("python/sentence_similarity.py"),
                resposta.getTexto(),
                List.of(proposicao.getTexto(), proposicao.getRespostas().stream().toList())
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
        List<Float> cosineScores = Arrays.stream(outputStream.toString().trim().split(", ")).map(Float::parseFloat).toList();

        if (!proposicao.getRespostas().contains(resposta))
            proposicao.getRespostas().add(resposta);

        proposicaoRepository.save(proposicao);
    }

    @Override
    public boolean addRemoveFollower(int idProposicao, int idSeguidor) throws IllegalOperationException {
        Proposicao proposicao = proposicaoRepository
                .findById(idProposicao)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Usuario seguidor = usuarioRepository
                .findById(idSeguidor)
                .orElseThrow(() -> new EntityNotFoundException("Seguidor não encontrada."));

        if (proposicao.getSeguidores().contains(seguidor)) {
            proposicao.getSeguidores().remove(seguidor);
            proposicaoRepository.save(proposicao);
            return false;
        }
        proposicao.getSeguidores().add(seguidor);
        proposicaoRepository.save(proposicao);
        return true;
    }

    @Override
    public void deleteById(int id) {
        proposicaoRepository.deleteById(id);
    }
}
