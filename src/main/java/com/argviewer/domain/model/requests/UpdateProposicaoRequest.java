package com.argviewer.domain.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProposicaoRequest {
    private int id;
    private String texto;
    @URL(regexp = "http")
    private String fonte;
    private int qtdUpvotes;
    private int qtdDownvotes;
    private int relevancia;
    private int veracidade;
}
