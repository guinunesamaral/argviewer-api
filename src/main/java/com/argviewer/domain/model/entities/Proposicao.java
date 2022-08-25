package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "proposicao")
@Getter
@Setter
public class Proposicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 400)
    private String texto;

    @Column(length = 300)
    private String fonte;

    @Column(nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    private LocalDateTime dataAlteracao;

    @Column(nullable = false)
    private int qtdUpvotes;

    @Column(nullable = false)
    private int qtdDownvotes;

    @Column(nullable = false)
    private int relevancia;

    @Column(nullable = false)
    private int veracidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Proposicao_Usuario"))
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "proposicao_seguidor",
            joinColumns = @JoinColumn(name = "proposicao_id", foreignKey = @ForeignKey(name = "FK_ProposicaoSeguidor_Proposicao")),
            inverseJoinColumns = @JoinColumn(name = "seguidor_id", foreignKey = @ForeignKey(name = "FK_ProposicaoSeguidor_Seguidor")))
    private Set<Usuario> seguidores;

    @ManyToMany
    @JoinTable(
            name = "proposicao_resposta",
            joinColumns = @JoinColumn(name = "proposicao_id", foreignKey = @ForeignKey(name = "FK_ProposicaoResposta_Proposicao")),
            inverseJoinColumns = @JoinColumn(name = "resposta_id", foreignKey = @ForeignKey(name = "FK_ProposicaoResposta_Resposta")))
    private Set<Proposicao> respostas;

    @ManyToMany(mappedBy = "respostas")
    private Set<Proposicao> proposicoes;

    public Proposicao() {
    }

    public Proposicao(int id, String texto, String fonte, Usuario usuario) {
        this.id = id;
        this.texto = texto;
        this.fonte = fonte;
        this.usuario = usuario;
    }
}
