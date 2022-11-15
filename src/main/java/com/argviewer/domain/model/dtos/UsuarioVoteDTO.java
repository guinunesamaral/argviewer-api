package com.argviewer.domain.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioVoteDTO {
    private UsuarioVoteIdDTO id;
    private UsuarioDTO usuario;
    private ProposicaoDTO proposicao;
    private boolean isUpvote;
}
