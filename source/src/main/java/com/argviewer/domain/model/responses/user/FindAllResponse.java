package com.argviewer.domain.model.responses.user;

import com.argviewer.domain.model.dtos.UsuarioDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FindAllResponse {
    private Iterable<UsuarioDto> usuarios;

    public FindAllResponse(Iterable<UsuarioDto> usuarios) {
        this.usuarios = usuarios;
    }
}
