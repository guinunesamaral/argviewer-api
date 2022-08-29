package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Tag {
    @Id
    private int id;

    @Column(nullable = false, length = 30)
    private String titulo;

    @Column(nullable = false, length = 300)
    private String descricao;

    @ManyToMany(mappedBy = "tags")
    private Set<Proposicao> proposicoes;

    public Tag() {
    }

    public Tag(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
