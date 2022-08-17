package com.argviewer.services;

import com.argviewer.domain.interfaces.mapper.ProposicaoMapper;
import com.argviewer.domain.interfaces.repository.ProposicaoRepository;
import com.argviewer.domain.interfaces.repository.UsuarioRepository;
import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.entities.Proposicao;
import com.argviewer.domain.model.entities.Usuario;
import com.argviewer.domain.model.exceptions.EntityNotFoundException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/main/resources/" + filename);
        return file.getAbsolutePath();
    }

    @Override
    public int create(ProposicaoDTO dto) {
        // Buscar proposições na tabela proposicao_tem_resposta e ver se alguma possui o texto do dto acima
        String line = String.format(
                "python %s %s %s",
                resolvePythonScriptPath("python/sentence_similarity.py"),
                dto.getTexto(),
                "['The dog plays in the garden', 'A woman watches TV', 'The cat sits inside']");
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

        Proposicao proposicao = proposicaoMapper.dtoToProposicao(dto);

        return proposicaoRepository.save(proposicao).getId();
    }

    @Override
    public void addResposta(int idProposicao, int idResposta) {
        Proposicao proposicao = proposicaoRepository
                .findById(idProposicao)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Proposicao resposta = proposicaoRepository
                .findById(idResposta)
                .orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada"));

        if (!proposicao.getRespostas().contains(resposta))
            proposicao.getRespostas().add(resposta);

        proposicaoRepository.save(proposicao);
    }

    @Override
    public void addSeguidor(int idProposicao, int idSeguidor) {
        Proposicao proposicao = proposicaoRepository
                .findById(idProposicao)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Usuario seguidor = usuarioRepository
                .findById(idSeguidor)
                .orElseThrow(() -> new EntityNotFoundException("Seguidor não encontrada."));

        if (!proposicao.getSeguidores().contains(seguidor))
            proposicao.getSeguidores().add(seguidor);

        proposicaoRepository.save(proposicao);
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
    public ProposicaoDTO findById(int id) {
        Proposicao proposicao = proposicaoRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));
        return proposicaoMapper.proposicaoToDTO(proposicao);
    }

    @Override
    public Set<ProposicaoDTO> findAll(Integer idUsuario) {
        Set<Proposicao> proposicoes = idUsuario == null
                ? Set.copyOf(proposicaoRepository.findAll())
                : proposicaoRepository.findByIdUsuario(idUsuario);
        return proposicaoMapper.proposicoesToDtoList(proposicoes);
    }

    @Override
    public long count(Integer idUsuario) {
        return idUsuario == null
                ? proposicaoRepository.count()
                : proposicaoRepository.countByIdUsuario(idUsuario);
    }

    @Override
    public void deleteById(int id) {
        proposicaoRepository.deleteById(id);
    }
}
