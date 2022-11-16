package com.argviewer.domain.model.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FindProposicaoResponse {
    private int id;
    private String texto;
    private String fonte;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private int qtdUpvotes;
    private int qtdDownvotes;
    private boolean isProposicaoInicial;
    private Boolean isRespostaFavoravel;
    private List<FindRespostaResponse> respostas;
    private List<FindUsuarioVoteResponse> votes;
}
