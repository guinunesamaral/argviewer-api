package com.argviewer.domain.interfaces.repositories;

import com.argviewer.domain.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {
    Optional<Usuario> findByNickname(String nickname);
}
