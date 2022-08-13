package com.argviewer.domain.model.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TipoAcaoDTO {

    @NonNull
    private int id;

    @NonNull
    private String titulo;

    @NonNull
    private String descricao;

    private List<HistoricoDTO> historicos;
}
