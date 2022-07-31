package com.argviewer.infrastructure.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class Elo {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String titulo;

    @NotNull
    private String descricao;
}
