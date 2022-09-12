package com.argviewer.domain.model.requests;

import com.argviewer.domain.model.dtos.TagDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProposicaoRequest {
    private String texto;
    private String fonte;
    private int usuarioId;
    private int[] tagIds;
}
