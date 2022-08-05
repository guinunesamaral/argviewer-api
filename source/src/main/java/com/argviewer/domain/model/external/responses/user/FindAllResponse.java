package com.argviewer.domain.model.external.responses.user;

import com.argviewer.domain.model.external.UsuarioExternal;

import java.util.List;

public class FindAllResponse {
    public List<UsuarioExternal> usuarios;

    public FindAllResponse(List<UsuarioExternal> usuarios) {
        this.usuarios = usuarios;
    }
}
