package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false, length = 30)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "tipo_acao_id", nullable = false)
    private TipoAcao tipoAcao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
