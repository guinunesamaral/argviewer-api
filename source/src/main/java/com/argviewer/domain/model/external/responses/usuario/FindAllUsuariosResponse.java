package com.argviewer.domain.model.external.responses.usuario;

import com.argviewer.domain.model.external.UsuarioExternal;

import java.util.List;

public class FindAllUsuariosResponse {
    public List<UsuarioExternal> usuarios;

    public FindAllUsuariosResponse(List<UsuarioExternal> usuarios) {
        this.usuarios = usuarios;
    }
}
