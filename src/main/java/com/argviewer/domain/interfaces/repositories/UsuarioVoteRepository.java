package com.argviewer.domain.interfaces.repositories;

import com.argviewer.domain.model.entities.UsuarioVote;
import com.argviewer.domain.model.entities.UsuarioVoteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioVoteRepository extends JpaRepository<UsuarioVote, UsuarioVoteId> {

    Optional<UsuarioVote> findByUsuarioIdAndProposicaoId(int usuarioId, int proposicaoId);

    void deleteByUsuarioIdAndProposicaoId(int usuarioId, int proposicaoId);
}
