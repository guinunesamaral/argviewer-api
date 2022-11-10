package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;

import java.util.List;
import java.util.Optional;

public interface ProposicaoService {

    List<ProposicaoDTO> find(Integer usuarioId, Integer tagId);

    Optional<ProposicaoDTO> findById(int proposicaoId);

    List<ProposicaoDTO> findByTextoContaining(String value);

    List<ProposicaoDTO> findRespostas(int proposicaoId);

    int create(ProposicaoDTO dto);

    void update(ProposicaoDTO dto);

    void addResposta(int proposicaoId, int respostaId) throws IllegalOperationException;

//    void addSeguidor(int proposicaoId, int seguidorId) throws IllegalOperationException;

//    void removeSeguidor(int proposicaoId, int seguidorId) throws IllegalOperationException;

    void deleteById(int proposicaoId);
}
