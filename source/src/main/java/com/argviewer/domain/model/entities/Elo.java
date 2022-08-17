package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "elo")
@Table(uniqueConstraints = @UniqueConstraint(name = "UQ_Elo_Titulo", columnNames = "titulo"))
@Getter
@Setter
public class Elo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    private String titulo;

    @Column(nullable = false, length = 300)
    private String descricao;

    @OneToMany(mappedBy = "elo")
    private Set<Usuario> usuarios;

    public Elo() {
    }

    public Elo(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
