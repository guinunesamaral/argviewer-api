package com.argviewer.domain.model.external.requests.usuario;

import com.argviewer.domain.model.external.UsuarioExternal;

public class SaveUsuarioRequest extends UsuarioExternal {
    public SaveUsuarioRequest(String nome, String nickname, String email, String senha) {
        super(nome, nickname, email, senha);
    }
}
