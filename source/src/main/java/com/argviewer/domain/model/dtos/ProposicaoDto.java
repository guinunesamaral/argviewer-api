package com.argviewer.domain.model.dtos;

import java.util.Date;

public record ProposicaoDto(
        int id,
        String texto,
        String fonte,
        Date dataCriacao,
        Date dataAlteracao,
        int qtdUpvotes,
        int qtdDownvotes,
        int relevancia,
        int veracidade,
        int idUsuario) {
}
