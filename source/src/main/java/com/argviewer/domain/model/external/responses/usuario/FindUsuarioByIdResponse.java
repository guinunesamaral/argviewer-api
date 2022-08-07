package com.argviewer.domain.model.external.responses.usuario;

import com.argviewer.domain.model.external.UsuarioExternal;

public class FindUsuarioByIdResponse {
    public UsuarioExternal usuario;

    public FindUsuarioByIdResponse(UsuarioExternal usuario) {
        this.usuario = usuario;
    }
}
