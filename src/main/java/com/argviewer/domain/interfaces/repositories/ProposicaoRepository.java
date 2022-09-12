package com.argviewer.domain.interfaces.repositories;

import com.argviewer.domain.model.entities.Proposicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProposicaoRepository extends JpaRepository<Proposicao, Integer>, JpaSpecificationExecutor<Proposicao> {
}
