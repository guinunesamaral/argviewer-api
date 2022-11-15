package com.argviewer.domain.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindUsuarioVoteResponse {
    private int usuarioId;
    private boolean isUpvote;
}
