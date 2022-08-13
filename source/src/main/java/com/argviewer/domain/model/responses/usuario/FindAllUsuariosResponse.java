package com.argviewer.domain.model.responses.usuario;

import com.argviewer.domain.model.dtos.UsuarioDTO;

import java.util.List;

public class FindAllUsuariosResponse {
    public List<UsuarioDTO> usuarios;

    public FindAllUsuariosResponse(List<UsuarioDTO> usuarios) {

    }
}
