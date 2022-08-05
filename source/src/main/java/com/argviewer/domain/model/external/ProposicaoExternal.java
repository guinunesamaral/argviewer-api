package com.argviewer.domain.model.external;

import java.util.List;
import java.util.Date;

public class ProposicaoExternal {
    public int id;
    public String texto;
    public String fonte;
    public Date dataCriacao;
    public Date dataAlteracao;
    public int qtdUpvotes;
    public int qtdDownvotes;
    public int relevancia;
    public int veracidade;
    public UsuarioExternal usuario;
    public List<UsuarioExternal> seguidores;
    public List<ProposicaoExternal> respostas;
}
