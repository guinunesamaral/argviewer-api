package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    void login(String emailNickname, String senha);

    List<UsuarioDTO> find(String value);

    Optional<UsuarioDTO> findById(int usuarioId);

//    List<UsuarioDTO> findSeguidores(int usuarioId);

//    List<UsuarioDTO> findSeguindo(int usuarioId);

    int create(UsuarioDTO dto);

    void update(UsuarioDTO dto) throws IllegalOperationException;

    void inactivate(int usuarioId) throws IllegalOperationException;

//    boolean saveSeguidores(int usuarioId, int seguidorId) throws IllegalOperationException;

//    boolean saveSeguindo(int usuarioId, int seguindoId) throws IllegalOperationException;
}
