package com.argviewer.infrastructure.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @NotNull
    private String Nome;

    @NotNull
    private String Nickname;

    @NotNull
    @Email
    private String Email;

    @NotNull
    private Date DataCriacao;

    @Null
    private Date DataAlteracao;

    @NotNull
    private byte[] Foto;

    @NotNull
    @ManyToOne
    private Elo elo;

    @Null
    @ManyToMany
    private List<Proposicao> proposicoesSeguindo;
}
