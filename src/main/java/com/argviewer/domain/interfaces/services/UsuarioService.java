package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;

import java.util.Optional;
import java.util.Set;

public interface UsuarioService {
    void login(String emailNickname, String senha);

    Set<UsuarioDTO> find(String value);

    Optional<UsuarioDTO> findById(int id);

    int create(UsuarioDTO dto);

    void update(UsuarioDTO dto) throws IllegalOperationException;

    void inactivate(int id) throws IllegalOperationException;

    boolean addRemoveFollower(int id, int followerId) throws IllegalOperationException;

    boolean addRemoveFollowing(int id, int followingId) throws IllegalOperationException;
}
