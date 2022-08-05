package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.internal.dtos.ProposicaoDTO;
import com.argviewer.domain.model.internal.dtos.UsuarioDTO;

import java.util.ArrayList;

public interface ProposicaoService {
    Integer save(UsuarioDTO entity);
    ProposicaoDTO findById(Integer id);
    boolean existsById(Integer integer);
    ArrayList<UsuarioDTO> findAll();
    long count();
    void deleteById(Integer id);
    void delete(UsuarioDTO entity);
}
