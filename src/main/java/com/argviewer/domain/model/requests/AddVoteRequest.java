package com.argviewer.domain.model.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddVoteRequest {
    private int usuarioId;
    private int proposicaoId;
    private boolean isUpvote;
}
