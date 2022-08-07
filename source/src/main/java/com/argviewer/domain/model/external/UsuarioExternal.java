package com.argviewer.domain.model.external;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioExternal {
    public int id;
    @NotEmpty(message = "Nome não pode estar vazio.")
    public String nome;
    @NotEmpty(message = "Nickname não pode estar vazio.")
    public String nickname;
    @Email(message = "Digite um email válido, por favor.")
    public String email;
    @NotEmpty(message = "Senha não pode estar vazia.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z\\d\\s])([A-Za-z\\d]|[^a-zA-Z\\d\\s]){8,40}$")
    public String senha;
    public LocalDateTime dataCriacao;
    public LocalDateTime dataAlteracao;
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

    public UsuarioExternal(int id) {
        this.id = id;
    }

    public UsuarioExternal(String nome, String nickname, String email, String senha) {
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
    }
}