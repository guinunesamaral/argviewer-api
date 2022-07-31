package com.argviewer.infrastructure.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class Proposicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String texto;

    @Null
    private String fonte;

    @NotNull
    private Date dataCriacao;

    @Null
    private Date dataAlteracao;

    @NotNull
    private Integer qtdUpvotes;

    @NotNull
    private Integer qtdDownvotes;

    @NotNull
    private Integer relevancia;

    @NotNull
    private Integer veracidade;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @Null
    @ManyToMany
    private List<Proposicao> argumentos;
}
