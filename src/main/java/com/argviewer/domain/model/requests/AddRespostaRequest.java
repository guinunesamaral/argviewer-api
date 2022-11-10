package com.argviewer.domain.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddRespostaRequest {
    private String texto;
    private String fonte;
    private Boolean isRespostaNegativa;
    private int usuarioId;
}
