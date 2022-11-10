package com.argviewer.domain.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class TagDTO {
    private int id;
    private String titulo;
    private String descricao;
    private List<ProposicaoDTO> proposicoes;

    public TagDTO() {
    }

    public TagDTO(int id) {
        this.id = id;
    }
}
