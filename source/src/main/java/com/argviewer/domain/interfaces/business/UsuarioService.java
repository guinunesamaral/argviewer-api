package com.argviewer.domain.interfaces.business;

import com.argviewer.domain.model.dtos.UsuarioDto;

import java.util.Optional;

public interface UsuarioService {
    Integer save(UsuarioDto entity);
    Optional<UsuarioDto> findById(Integer id);
    boolean existsById(Integer integer);
    Iterable<UsuarioDto> findAll();
    long count();
    void deleteById(Integer id);
    void delete(UsuarioDto entity);
}
