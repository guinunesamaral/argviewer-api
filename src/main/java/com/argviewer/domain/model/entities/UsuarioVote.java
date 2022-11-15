package com.argviewer.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsuarioVote {

    @EmbeddedId
    private UsuarioVoteId id;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("proposicaoId")
    @JoinColumn(name = "proposicao_id")
    private Proposicao proposicao;

    @Column(nullable = false)
    boolean isUpvote;
}
