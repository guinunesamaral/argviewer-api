package com.argviewer.domain.model.internal.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class UsuarioDTO {

	private Integer id;

	@NonNull
	private String nome;

	@NonNull
	private String nickname;

	@NonNull
	private String email;

	@NonNull
	private String senha;

	@NonNull
	private LocalDateTime dataCriacao;

	private LocalDateTime dataAlteracao;

	private byte[] foto;

	@NonNull
	private Boolean isAnonimo;

	@NonNull
	private Boolean isModerador;

	private EloDTO elo;

	private List<HistoricoDTO> historicos;

	private List<ProposicaoDTO> proposicoesCriadas;

	private List<ProposicaoDTO> proposicoesSeguindo;

	private List<UsuarioDTO> seguidores;

	private List<UsuarioDTO> seguindo;
}
