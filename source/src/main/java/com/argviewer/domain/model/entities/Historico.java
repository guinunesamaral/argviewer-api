package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "historico")
@Getter
@Setter
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date data;

    @ManyToOne
    @JoinColumn(name = "tipo_acao_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Historico_TipoAcao"))
    private TipoAcao tipoAcao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Historico_Usuario"))
    private Usuario usuario;
}
