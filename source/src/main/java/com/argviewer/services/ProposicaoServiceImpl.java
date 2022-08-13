package com.argviewer.services;

import com.argviewer.domain.interfaces.mapper.ProposicaoMapper;
import com.argviewer.domain.interfaces.repository.ProposicaoRepository;
import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.entities.Proposicao;
import org.springframework.stereotype.Service;

import javax.script.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.List;

@Service
public class ProposicaoServiceImpl implements ProposicaoService {

    private final ProposicaoRepository proposicaoRepository;

    private final ProposicaoMapper proposicaoMapper;

    public ProposicaoServiceImpl(ProposicaoRepository proposicaoRepository, ProposicaoMapper proposicaoMapper) {
        this.proposicaoRepository = proposicaoRepository;
        this.proposicaoMapper = proposicaoMapper;
    }

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/resources/python/" + filename);
        return file.getAbsolutePath();
    }

    @Override
    public int create(ProposicaoDTO dto) {
        StringWriter writer = new StringWriter();
        ScriptContext context = new SimpleScriptContext();
        context.setWriter(writer);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("python");

        try {
            engine.eval(new FileReader(resolvePythonScriptPath("sentence_similarity.py")), context);
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
//        return engine.;
        return proposicaoRepository.save(proposicaoMapper.dtoToProposicao(dto)).getId();
    }

    @Override
    public void update(ProposicaoDTO dto) {
        Proposicao proposicao = proposicaoRepository.findById(dto.getId()).orElseThrow();
        proposicaoMapper.dtoToProposicao(dto, proposicao);
        proposicaoRepository.save(proposicao);
    }

    @Override
    public ProposicaoDTO findById(int id) {
        return proposicaoMapper.proposicaoToDTO(proposicaoRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ProposicaoDTO> findAll(Integer idUsuario) {
        List<Proposicao> proposicoes = idUsuario == null
                ? proposicaoRepository.findAll()
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
