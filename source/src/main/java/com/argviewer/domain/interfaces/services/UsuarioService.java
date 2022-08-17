package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;

import java.util.Set;

public interface UsuarioService {

    int create(UsuarioDTO dto);

    void update(UsuarioDTO dto) throws IllegalOperationException;

    UsuarioDTO findById(int id);

    Set<UsuarioDTO> findAll();

    long count();

    void inactivate(int id);
}
