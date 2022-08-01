package com.argviewer.domain.model.dtos;

import java.util.Date;

public record UsuarioDto(
		int id,
		String nome,
		String nickname,
		String email,
		String senha,
		Date dataCriacao,
		Date dataAlteracao,
		byte[] foto,
		boolean isAnonimo,
		boolean isModerador,
		EloDto elo) {
}
