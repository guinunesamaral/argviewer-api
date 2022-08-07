package com.argviewer.domain.model.external;

import javax.validation.constraints.Max;
import java.time.LocalDateTime;
import java.util.List;

public class ProposicaoExternal {
    public int id;
    @Max(value = 400, message = "A proposição deve ter até 400 caracteres.")
    public String texto;
    @Max(value = 300, message = "A fonte deve ter até 300 caracteres.")
    public String fonte;
    public LocalDateTime dataCriacao;
    public LocalDateTime dataAlteracao;
    public int qtdUpvotes;
    public int qtdDownvotes;
    public int relevancia;
    public int veracidade;
    public UsuarioExternal usuario;
    public List<UsuarioExternal> seguidores;
    public List<ProposicaoExternal> respostas;

    public ProposicaoExternal() {
    }

    public ProposicaoExternal(String texto, String fonte, UsuarioExternal usuario) {
        this.texto = texto;
        this.fonte = fonte;
        this.usuario = usuario;
    }

    public ProposicaoExternal(int id, String texto, String fonte, LocalDateTime dataCriacao, LocalDateTime dataAlteracao, int qtdUpvotes, int qtdDownvotes, int relevancia, int veracidade, UsuarioExternal usuario) {
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
    }

    public ProposicaoExternal(ProposicaoExternal proposicao) {
        this(proposicao.id, proposicao.texto, proposicao.fonte, proposicao.dataCriacao, proposicao.dataAlteracao, proposicao.qtdUpvotes, proposicao.qtdDownvotes, proposicao.relevancia, proposicao.veracidade, proposicao.usuario);
        this.seguidores = proposicao.seguidores;
        this.respostas = proposicao.respostas;
    }
}
