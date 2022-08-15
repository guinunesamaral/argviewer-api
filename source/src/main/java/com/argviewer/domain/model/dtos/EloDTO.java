package com.argviewer.domain.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class EloDTO {
    private int id;
    private String titulo;
    private String descricao;
    private List<UsuarioDTO> usuarios;

    public EloDTO(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarios = Collections.emptyList();
    }
}
