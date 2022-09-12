package com.argviewer.domain.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProposicaoRequest {
    private int id;
    private String texto;
    private String fonte;
    private int qtdUpvotes;
    private int qtdDownvotes;
    private int relevancia;
    private int veracidade;
    private int[] tagIds;
}
