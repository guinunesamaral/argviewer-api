package com.argviewer.domain.model.internal.dtos;

import lombok.*;

import java.util.List;
import java.util.Date;

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
    private Date dataCriacao;

    private Date dataAlteracao;

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
