package com.argviewer.domain.model.internal.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ProposicaoDTO {

    @NonNull
    private Integer id;

    @NonNull
    private String texto;

    @NonNull
    private String fonte;

    @NonNull
    private LocalDateTime dataCriacao;

    private LocalDateTime dataAlteracao;

    @NonNull
    private Integer qtdUpvotes;

    @NonNull
    private Integer qtdDownvotes;

    @NonNull
    private Integer relevancia;

    @NonNull
    private Integer veracidade;

    @NonNull
    private UsuarioDTO usuario;

    private List<UsuarioDTO> seguidores;

    private List<ProposicaoDTO> respostas;
}
