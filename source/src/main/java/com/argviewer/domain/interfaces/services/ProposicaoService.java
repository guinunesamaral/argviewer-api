package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.internal.dtos.ProposicaoDTO;

import java.util.List;

public interface ProposicaoService {

    int save(ProposicaoDTO entity);

    ProposicaoDTO findById(Integer id);

    boolean existsById(Integer integer);

    List<ProposicaoDTO> findAll();

    long count();

    void deleteById(Integer id);

    void delete(ProposicaoDTO entity);
}
