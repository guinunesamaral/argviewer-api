package com.argviewer.domain.model.requests.usuario;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveUsuarioRequest extends UsuarioDTO {

    @JsonCreator
    public SaveUsuarioRequest(@JsonProperty("nome") String nome, @JsonProperty("nickname") String nickname, @JsonProperty("email") String email, @JsonProperty("senha") String senha) {
        super(nome, nickname, email, senha);
    }
}
