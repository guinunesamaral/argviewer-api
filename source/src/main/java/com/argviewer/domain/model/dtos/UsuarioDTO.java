package com.argviewer.domain.model.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
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
	private boolean isModerador;
	private EloDTO elo;
	private List<HistoricoDTO> historicos;
	private List<ProposicaoDTO> proposicoesCriadas;
	private List<ProposicaoDTO> proposicoesSeguindo;
	private List<UsuarioDTO> seguidores;
	private List<UsuarioDTO> seguindo;

	public UsuarioDTO() {
	}

	public UsuarioDTO(int id) {
		this.id = id;
	}

	public UsuarioDTO(String nome, String nickname, String email, String senha) {
		this.nome = nome;
		this.nickname = nickname;
		this.email = email;
		this.senha = senha;
	}

	public UsuarioDTO(int id, String nome, String nickname, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.nickname = nickname;
		this.email = email;
		this.senha = senha;
	}

	public UsuarioDTO(UsuarioDTO usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getNickname(), usuario.getEmail(), usuario.getSenha(), usuario.getDataCriacao(), usuario.getDataAlteracao(), usuario.getFoto(), usuario.isActive(), usuario.isAnonimo(), usuario.isModerador(), usuario.getElo(), usuario.getHistoricos(), usuario.getProposicoesCriadas(), usuario.getProposicoesSeguindo(), usuario.getSeguidores(), usuario.getSeguindo());
	}
}
