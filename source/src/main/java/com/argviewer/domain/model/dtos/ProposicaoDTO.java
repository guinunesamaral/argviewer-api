package com.argviewer.domain.model.dtos;

import lombok.*;

import java.time.LocalDateTime;
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

    public ProposicaoDTO() {
    }

    public ProposicaoDTO(String texto, String fonte, UsuarioDTO usuario) {
        this.texto = texto;
        this.fonte = fonte;
        this.usuario = usuario;
    }

    public ProposicaoDTO(int id, String texto, String fonte, LocalDateTime dataCriacao, LocalDateTime dataAlteracao, int qtdUpvotes, int qtdDownvotes, int relevancia, int veracidade, UsuarioDTO usuario, List<UsuarioDTO> seguidores, List<ProposicaoDTO> respostas) {
        this.id = id;
        this.texto = texto;
        this.fonte = fonte;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
        this.qtdUpvotes = qtdUpvotes;
        this.qtdDownvotes = qtdDownvotes;
        this.relevancia = relevancia;
        this.veracidade = veracidade;
        this.usuario = usuario;
        this.seguidores = seguidores;
        this.respostas = respostas;
    }
}
