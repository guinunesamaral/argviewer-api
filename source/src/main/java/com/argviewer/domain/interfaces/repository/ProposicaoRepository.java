package com.argviewer.domain.interfaces.repository;

import com.argviewer.domain.model.internal.entities.Proposicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProposicaoRepository extends JpaRepository<Proposicao, Integer> {
    Proposicao findByTexto(String texto);
}
