package com.argviewer.domain.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProposicaoDTO {
    private int id;
    private String texto;
    private String fonte;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private int qtdUpvotes;
    private int qtdDownvotes;
    private boolean isProposicaoInicial;
    private Boolean isRespostaContraria;
    private UsuarioDTO usuario;
    private List<TagDTO> tags;
    private List<ProposicaoDTO> respostas;
    private List<UsuarioVoteDTO> votes;

    public ProposicaoDTO() {
    }

    public ProposicaoDTO(int id) {
        this.id = id;
    }
}
