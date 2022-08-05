package com.argviewer.domain.interfaces.repository;

import com.argviewer.domain.model.internal.entities.TipoAcao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoAcaoRepository extends JpaRepository<TipoAcao, Integer> {
}
