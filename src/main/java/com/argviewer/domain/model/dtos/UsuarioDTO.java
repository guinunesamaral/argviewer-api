package com.argviewer.domain.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
	private int id;
	private String nome;
	private String nickname;
	private String email;
	private String senha;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAlteracao;
	private byte[] foto;
	private boolean isActive;
	private boolean isAnonimo;
	private List<ProposicaoDTO> proposicoesCriadas;

	public UsuarioDTO() {
	}

	public UsuarioDTO(int id) {
		this.id = id;
	}
}
