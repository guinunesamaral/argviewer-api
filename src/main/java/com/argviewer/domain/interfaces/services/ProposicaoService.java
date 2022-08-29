package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;

import java.util.Optional;
import java.util.Set;

public interface ProposicaoService {

    Set<ProposicaoDTO> find(Integer idUsuario);

    Optional<ProposicaoDTO> findById(int id);

    int create(ProposicaoDTO dto);

    void update(ProposicaoDTO dto);

    void addAnswer(int idProposicao, int idResposta);

    boolean addRemoveFollower(int idProposicao, int idSeguidor) throws IllegalOperationException;

    void deleteById(int id);
}
