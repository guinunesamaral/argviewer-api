package com.argviewer.domain.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ProposicaoDTO {
    private int id;
    private String texto;
    private String fonte;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private int qtdUpvotes;
    private int qtdDownvotes;
    private int relevancia;
    private int veracidade;
    private UsuarioDTO usuario;
    private List<UsuarioDTO> seguidores;
    private List<ProposicaoDTO> respostas;

    public ProposicaoDTO(String texto, String fonte, int idUsuario) {
        this.texto = texto;
        this.fonte = fonte;
        this.usuario = new UsuarioDTO(idUsuario, null, null, null, null);
        this.seguidores = Collections.emptyList();
        this.respostas = Collections.emptyList();
    }
}
