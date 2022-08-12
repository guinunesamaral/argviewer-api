package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    int create(UsuarioDTO dto);

    void update(UsuarioDTO dto);

    UsuarioDTO findById(int id);

    List<UsuarioDTO> findAll();

    long count();

    void inactivate(int id);
}
