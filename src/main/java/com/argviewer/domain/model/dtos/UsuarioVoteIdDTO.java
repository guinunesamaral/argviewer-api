package com.argviewer.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioVoteIdDTO {
    private int usuarioId;
    private int proposicaoId;
}
