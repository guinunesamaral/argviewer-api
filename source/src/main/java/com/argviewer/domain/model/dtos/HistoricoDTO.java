package com.argviewer.domain.model.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class HistoricoDTO {

    @NonNull
    private int id;

    @NonNull
    private Date data;

    @NonNull
    private TipoAcaoDTO tipoAcao;

    @NonNull
    private UsuarioDTO usuario;
}
