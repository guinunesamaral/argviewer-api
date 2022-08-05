package com.argviewer.domain.model.external;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Date;

public class UsuarioExternal {
    public int id;
    public String nome;
    public String nickname;
    @Email
    public String email;
    public String senha;
    public Date dataCriacao;
    public Date dataAlteracao;
    public byte[] foto;
    public boolean isAnonimo;
    public boolean isModerador;
    public EloExternal elo;
    public List<HistoricoExternal> historicos;
    public List<ProposicaoExternal> proposicoesCriadas;
    public List<ProposicaoExternal> proposicoesSeguindo;
    public List<UsuarioExternal> seguidores;
    public List<UsuarioExternal> seguindo;

    public UsuarioExternal() {
    }

    public UsuarioExternal(String nome, String nickname, String email, String senha) {
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
    }
}