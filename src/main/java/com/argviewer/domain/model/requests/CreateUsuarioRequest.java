package com.argviewer.domain.model.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUsuarioRequest {
    private String nome;
    private String nickname;
    private String email;
    private String senha;
    private String foto;
}
