package com.argviewer.domain.model.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindEloResponse {
    private int id;
    private String titulo;
    private String descricao;
}
