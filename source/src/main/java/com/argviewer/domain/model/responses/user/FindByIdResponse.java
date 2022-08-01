package com.argviewer.domain.model.responses.user;

import com.argviewer.domain.model.dtos.UsuarioDto;

public record FindByIdResponse(java.util.Optional<UsuarioDto> usuario) {
}
