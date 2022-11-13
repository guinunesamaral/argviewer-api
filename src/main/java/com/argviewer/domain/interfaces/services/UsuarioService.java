package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
import com.argviewer.domain.model.exceptions.InvalidParameterException;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    void login(String emailNickname, String senha);

    List<UsuarioDTO> find(String value);

    UsuarioDTO findByEmail(String email);

    UsuarioDTO findByNickname(String nickname);

    Optional<UsuarioDTO> findById(int usuarioId);

    int create(UsuarioDTO dto) throws InvalidParameterException;

    void update(UsuarioDTO dto) throws IllegalOperationException, InvalidParameterException;

    void inactivate(int usuarioId) throws IllegalOperationException;
}
