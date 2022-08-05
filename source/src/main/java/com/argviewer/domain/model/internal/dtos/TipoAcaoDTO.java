package com.argviewer.domain.model.internal.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class TipoAcaoDTO {

    @NonNull
    private Integer id;

    @NonNull
    private String titulo;

    @NonNull
    private String descricao;

    private List<HistoricoDTO> historicos;
}
