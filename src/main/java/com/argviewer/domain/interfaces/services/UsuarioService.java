package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;

import java.util.Optional;
import java.util.Set;

public interface UsuarioService {
    void login(String emailNickname, String senha);

    Set<UsuarioDTO> find(String value);

    Optional<UsuarioDTO> findById(int usuarioId);

    Set<UsuarioDTO> findSeguidores(int usuarioId);

    Set<UsuarioDTO> findSeguindo(int usuarioId);

    int create(UsuarioDTO dto);

    void update(UsuarioDTO dto) throws IllegalOperationException;

    void inactivate(int usuarioId) throws IllegalOperationException;

    boolean saveSeguidores(int usuarioId, int seguidorId) throws IllegalOperationException;

    boolean saveSeguindo(int usuarioId, int seguindoId) throws IllegalOperationException;
}
