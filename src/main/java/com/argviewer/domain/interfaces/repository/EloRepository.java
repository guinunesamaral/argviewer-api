package com.argviewer.domain.interfaces.repository;

import com.argviewer.domain.model.entities.Elo;
import org.springframework.data.repository.CrudRepository;

public interface EloRepository extends CrudRepository<Elo, Integer> {
    Elo findByTitulo(String titulo);
}
