package com.argviewer.domain.interfaces.repository;

import com.argviewer.domain.model.entities.Proposicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProposicaoRepository extends JpaRepository<Proposicao, Integer> {
    Proposicao findByTexto(String texto);

    @Query(value = "SELECT p FROM proposicao p WHERE p.usuario_id = :id", nativeQuery = true)
    List<Proposicao> findByIdUsuario(@Param("id") int id);

    @Query(value = "SELECT COUNT(p) FROM proposicao p WHERE p.usuario_id = :id", nativeQuery = true)
    long countByIdUsuario(@Param("id") int id);
}
