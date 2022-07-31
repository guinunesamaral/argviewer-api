package com.argviewer.domain.model.entities;

import java.util.Date;

public record Proposicao(
        int id,
        String texto,
        String fonte,
        Date dataCriacao,
        Date DataAlteracao,
        int qtdUpvotes,
        int qtdDownvotes,
        int relevancia,
        int veracidade,
        int idUsuario) {

    public Proposicao(
            String texto,
            String fonte,
            Date dataCriacao,
            Date DataAlteracao,
            int qtdUpvotes,
            int qtdDownvotes,
            int relevancia,
            int veracidade,
            int idUsuario) {
        this(0, texto, fonte, dataCriacao, DataAlteracao, qtdUpvotes, qtdDownvotes, relevancia, veracidade, idUsuario);
    }

    public Proposicao(
            String texto,
            Date dataCriacao,
            Date DataAlteracao,
            int qtdUpvotes,
            int qtdDownvotes,
            int relevancia,
            int veracidade,
            int idUsuario) {
        this(0, texto, "", dataCriacao, DataAlteracao, qtdUpvotes, qtdDownvotes, relevancia, veracidade, idUsuario);
    }
}
