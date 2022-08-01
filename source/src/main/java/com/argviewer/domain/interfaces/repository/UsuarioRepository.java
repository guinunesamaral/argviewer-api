package com.argviewer.domain.interfaces.repository;

import com.argviewer.domain.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
