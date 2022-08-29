package com.argviewer.domain.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProposicaoRequest {
    private String texto;
    private String fonte;
    private int usuarioId;
}
