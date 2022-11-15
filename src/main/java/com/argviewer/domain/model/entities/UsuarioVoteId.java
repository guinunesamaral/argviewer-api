package com.argviewer.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UsuarioVoteId implements Serializable {

    @Column(name = "usuario_id")
    private int usuarioId;

    @Column(name = "proposicao_id")
    private int proposicaoId;
}
