package com.argviewer.services;

import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.internal.dtos.ProposicaoDTO;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

import javax.script.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;
import java.util.List;

@Service
public class ProposicaoServiceImpl implements ProposicaoService {

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/resources/python/" + filename);
        return file.getAbsolutePath();
    }

    @Override
    public int save(ProposicaoDTO entity) {
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
        return engine.;
    }

    @Override
    public ProposicaoDTO findById(Integer id) {
        return null;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<ProposicaoDTO> findAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void delete(ProposicaoDTO entity) {

    }
}
