package com.argviewer.domain.model.responses.proposicao;

import com.argviewer.domain.model.dtos.ProposicaoDTO;

import java.util.List;

public class FindAllProposicoesResponse {
    public List<ProposicaoDTO> proposicoes;

    public FindAllProposicoesResponse(List<ProposicaoDTO> proposicoes) {
        this.proposicoes = proposicoes;
    }
}
