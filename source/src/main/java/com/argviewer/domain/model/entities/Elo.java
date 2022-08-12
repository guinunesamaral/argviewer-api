package com.argviewer.domain.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "elo")
@Table(uniqueConstraints = {@UniqueConstraint(name = "UQ_Elo_Titulo", columnNames = "titulo")})
@Getter
@Setter
@AllArgsConstructor
public class Elo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    private String titulo;

    @Column(nullable = false, length = 300)
    private String descricao;

    @OneToMany(mappedBy = "elo")
    private List<Usuario> usuarios;

    public Elo() {
    }

    public Elo(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Elo(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }
}
