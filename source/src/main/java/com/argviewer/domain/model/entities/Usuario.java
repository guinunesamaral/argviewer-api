package com.argviewer.domain.model.entities;

import java.util.Date;

public record Usuario(
		int id,
		String nome,
		String nickname,
		String email,
		String senha,
		Date dataCriacao,
		Date dataAtualizacao) {

	public Usuario(
			String nome,
			String nickname,
			String email,
			String senha,
			Date dataCriacao,
			Date dataAtualizacao) {
		this(0, nome, nickname, email, senha, dataCriacao, dataAtualizacao);
	}
}
