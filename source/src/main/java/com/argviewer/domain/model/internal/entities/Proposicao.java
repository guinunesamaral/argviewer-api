package com.argviewer.domain.model.internal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UQ_Proposicao_Texto", columnNames = "texto")})
@Getter
@Setter
public class Proposicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 400)
    private String texto;

    @Column(length = 300)
    private String fonte;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    private LocalDateTime dataAlteracao;

    @Column(nullable = false)
    private Integer qtdUpvotes;

    @Column(nullable = false)
    private Integer qtdDownvotes;

    @Column(nullable = false)
    private Integer relevancia;

    @Column(nullable = false)
    private Integer veracidade;

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

    public Proposicao(String texto, LocalDateTime dataCriacao, Integer qtdUpvotes, Integer qtdDownvotes, Integer relevancia, Integer veracidade, Usuario usuario) {
        this.texto = texto;
        this.dataCriacao = dataCriacao;
        this.qtdUpvotes = qtdUpvotes;
        this.qtdDownvotes = qtdDownvotes;
        this.relevancia = relevancia;
        this.veracidade = veracidade;
        this.usuario = usuario;
    }
}
