package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
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
    private Date dataCriacao;

    private Date dataAlteracao;

    @Column(nullable = false)
    private Integer qtdUpvotes;

    @Column(nullable = false)
    private Integer qtdDownvotes;

    @Column(nullable = false)
    private Integer relevancia;

    @Column(nullable = false)
    private Integer veracidade;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany(mappedBy = "proposicoesSeguindo")
    private Set<Usuario> seguidores;

    @ManyToMany
    @JoinTable(
            name = "proposicao_tem_resposta",
            joinColumns = @JoinColumn(name = "proposicao_id"),
            inverseJoinColumns = @JoinColumn(name = "resposta_id"))
    private Set<Proposicao> respostas;

    @ManyToMany(mappedBy = "respostas")
    private Set<Proposicao> proposicoes;
}
