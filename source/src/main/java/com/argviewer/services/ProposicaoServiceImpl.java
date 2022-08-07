package com.argviewer.services;

import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.internal.dtos.ProposicaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposicaoServiceImpl implements ProposicaoService {

    @Override
    public int save(ProposicaoDTO entity) {
        return 0;
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
