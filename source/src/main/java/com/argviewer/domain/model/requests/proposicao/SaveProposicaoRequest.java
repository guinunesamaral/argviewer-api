package com.argviewer.domain.model.requests.proposicao;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveProposicaoRequest extends ProposicaoDTO {

    @JsonCreator
    public SaveProposicaoRequest(@JsonProperty("texto") String texto, @JsonProperty("fonte") String fonte, @JsonProperty("idUsuario") int idUsuario) {
        super(texto, fonte, new UsuarioDTO(idUsuario));
    }
}
