package com.argviewer.domain.model.requests.propositions;

import java.util.List;

import com.argviewer.domain.model.entities.Proposicao;

public record GetAllRequest(List<Proposicao> propositions) {

}
