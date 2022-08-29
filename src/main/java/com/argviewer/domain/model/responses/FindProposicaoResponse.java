package com.argviewer.domain.model.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private int relevancia;
    private int veracidade;
    private FindUsuarioResponse usuario;
}
