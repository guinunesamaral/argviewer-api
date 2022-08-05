package com.argviewer.domain.interfaces.repository;

import com.argviewer.domain.model.internal.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
