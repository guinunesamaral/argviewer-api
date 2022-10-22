package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;

import java.util.Optional;
import java.util.Set;

public interface ProposicaoService {

    Set<ProposicaoDTO> find(Integer usuarioId, Integer tagId);

    Optional<ProposicaoDTO> findById(int proposicaoId);

    Set<ProposicaoDTO> findByTextoContaining(String value);

    Set<ProposicaoDTO> findRespostas(int proposicaoId);

    int create(ProposicaoDTO dto);

    void update(ProposicaoDTO dto);

    boolean saveRespostas(int proposicaoId, int replicaId) throws IllegalOperationException;

    void addSeguidor(int proposicaoId, int seguidorId) throws IllegalOperationException;

    void removeSeguidor(int proposicaoId, int seguidorId) throws IllegalOperationException;

    void deleteById(int proposicaoId);
}
