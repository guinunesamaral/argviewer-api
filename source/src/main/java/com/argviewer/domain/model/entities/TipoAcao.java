package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
public class TipoAcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true, nullable = false, length = 30)
    private String titulo;

    @NotNull
    @Column(unique = true, nullable = false, length = 300)
    private String descricao;

    @OneToMany(mappedBy = "tipoAcao")
    private Set<Historico> historicos;
}
