package com.argviewer.domain.model.internal.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UQ_TipoAcao_Titulo", columnNames = "titulo")})
@Getter
@Setter
public class TipoAcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String titulo;

    @Column(nullable = false, length = 300)
    private String descricao;

    @OneToMany(mappedBy = "tipoAcao")
    private List<Historico> historicos;
}
