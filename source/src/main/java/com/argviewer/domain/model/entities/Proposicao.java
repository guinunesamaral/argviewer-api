package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "proposicao")
@Table(uniqueConstraints = {@UniqueConstraint(name = "UQ_Proposicao_Texto", columnNames = "texto")})
@Getter
@Setter
@NoArgsConstructor
public class Proposicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 400)
    private String texto;

    @Column(length = 300)
    private String fonte;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

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

    @ManyToMany(mappedBy = "proposicoesSeguindo")
    private List<Usuario> seguidores;

    @ManyToMany
    @JoinTable(
            name = "proposicao_tem_resposta",
            joinColumns = @JoinColumn(name = "proposicao_id"),
            inverseJoinColumns = @JoinColumn(name = "resposta_id", foreignKey = @ForeignKey(name = "FK_Proposicao_Resposta")))
    private List<Proposicao> respostas;

    @ManyToMany(mappedBy = "respostas")
    private List<Proposicao> proposicoes;

    public Proposicao(String texto, LocalDateTime dataCriacao, int qtdUpvotes, int qtdDownvotes, int relevancia, int veracidade, Usuario usuario) {
        this.texto = texto;
        this.dataCriacao = dataCriacao;
        this.qtdUpvotes = qtdUpvotes;
        this.qtdDownvotes = qtdDownvotes;
        this.relevancia = relevancia;
        this.veracidade = veracidade;
        this.usuario = usuario;
    }
}
