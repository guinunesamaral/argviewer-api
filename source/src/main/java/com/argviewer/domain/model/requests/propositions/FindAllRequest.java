package com.argviewer.domain.model.requests.propositions;

import java.util.List;

import com.argviewer.domain.model.dtos.ProposicaoDto;

public record FindAllRequest(List<ProposicaoDto> propositions) {

}
