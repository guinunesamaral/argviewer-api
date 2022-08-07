package com.argviewer.domain.model.external.responses.usuario;

import com.argviewer.domain.model.external.UsuarioExternal;

public class UpdateUsuarioResponse {
    public UsuarioExternal usuario;

    public UpdateUsuarioResponse(UsuarioExternal usuario) {
        this.usuario = usuario;
    }
}
