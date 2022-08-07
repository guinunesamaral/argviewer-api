package com.argviewer.domain.model.external.responses.proposicao;

import com.argviewer.domain.model.external.ProposicaoExternal;

import java.util.List;

public class FindAllProposicoesResponse {
    public List<ProposicaoExternal> proposicoes;

    public FindAllProposicoesResponse(List<ProposicaoExternal> proposicoes) {
        this.proposicoes = proposicoes;
    }
}
