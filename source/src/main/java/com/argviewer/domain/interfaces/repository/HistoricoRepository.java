package com.argviewer.domain.interfaces.repository;

import com.argviewer.domain.model.entities.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
}
