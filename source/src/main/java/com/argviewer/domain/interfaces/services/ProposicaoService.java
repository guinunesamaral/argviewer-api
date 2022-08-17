package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.ProposicaoDTO;

import java.util.Set;

public interface ProposicaoService {

    int create(ProposicaoDTO dto);

    void addResposta(int idProposicao, int idResposta);

    void addSeguidor(int idProposicao, int idSeguidor);

    void update(ProposicaoDTO dto);

    ProposicaoDTO findById(int id);

    Set<ProposicaoDTO> findAll(Integer idUsuario);

    long count(Integer idUsuario);

    void deleteById(int id);
}
