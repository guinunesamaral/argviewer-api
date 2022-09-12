package com.argviewer.domain.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Set;

@Getter
@Setter
public class TagDTO {
    private int id;
    private String titulo;
    private String descricao;
    private Set<ProposicaoDTO> proposicoes;
    private Set<TagDTO> tags;

    public TagDTO() {
    }

    public TagDTO(int id) {
        this.id = id;
    }
}
