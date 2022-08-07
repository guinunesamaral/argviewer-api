package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.internal.dtos.UsuarioDTO;

import java.util.List;

public interface UsuarioService {

    int save(UsuarioDTO entity);

    UsuarioDTO findById(Integer id);

    boolean existsById(Integer integer);

    List<UsuarioDTO> findAll();

    long count();

    void deleteById(Integer id);

    void delete(UsuarioDTO entity);
}
