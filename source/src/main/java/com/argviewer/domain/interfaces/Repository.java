package com.argviewer.domain.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.argviewer.infrastructure.repository.Usuario;

public interface Repository extends CrudRepository<Usuario, Integer> {
}
