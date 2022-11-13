package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
import com.argviewer.domain.model.exceptions.InvalidParameterException;

import java.util.List;
import java.util.Optional;

public interface ProposicaoService {

    List<ProposicaoDTO> find(Integer usuarioId, Integer tagId);

    Optional<ProposicaoDTO> findById(int proposicaoId);

    List<ProposicaoDTO> findByTextoContaining(String value);

    List<ProposicaoDTO> findRespostas(int proposicaoId);

    int create(ProposicaoDTO dto) throws InvalidParameterException;

    void update(ProposicaoDTO dto) throws InvalidParameterException;

    void addResposta(int proposicaoId, ProposicaoDTO dto) throws IllegalOperationException;

    void deleteById(int proposicaoId);
}
