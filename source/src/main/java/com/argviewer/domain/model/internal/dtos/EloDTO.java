package com.argviewer.domain.model.internal.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class EloDTO {

    @NonNull
    private Integer id;

    @NonNull
    private String titulo;

    @NonNull
    private String descricao;

    private List<UsuarioDTO> usuarios;
}
