package com.argviewer.domain.model.external.requests.proposicao;

import com.argviewer.domain.model.external.ProposicaoExternal;
import com.argviewer.domain.model.external.UsuarioExternal;

public class SaveProposicaoRequest extends ProposicaoExternal {

    public SaveProposicaoRequest(String texto, String fonte, int idUsuario) {
        super(texto, fonte, new UsuarioExternal(idUsuario));
    }
}
