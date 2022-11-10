package com.argviewer.domain.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUsuarioRequest {
    private int id;
    private String nome;
    private String nickname;
    private String email;
    private String senha;
    private String foto;
    private boolean isAnonimo;
}
