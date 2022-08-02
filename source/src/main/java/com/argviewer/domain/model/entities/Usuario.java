package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Email
    @Column(unique = true, nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 128)
    private String senha;

    @Column(nullable = false)
    @CreatedDate
    private Date dataCriacao;

    @LastModifiedDate
    private Date dataAlteracao;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] foto;

    @Column(nullable = false)
    private Boolean isAnonimo;

    @Column(nullable = false)
    private Boolean isModerador;

    @ManyToOne
    @JoinColumn(name = "elo_id", nullable = false)
    private Elo elo;

    @OneToMany(mappedBy = "usuario")
    private Set<Historico> historicos;

    @OneToMany(mappedBy = "usuario")
    private Set<Proposicao> proposicoesCriadas;

    @ManyToMany()
    @JoinTable(
            name = "usuario_segue_proposicao",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "proposicao_id"))
    private Set<Proposicao> proposicoesSeguindo;

    @ManyToMany()
    @JoinTable(
            name = "usuario_tem_seguidor",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "seguidor_id"))
    private Set<Usuario> seguidores;

    @ManyToMany(mappedBy = "seguidores")
    private Set<Usuario> seguindo;

    public Usuario(String nome, String nickname, String email, String senha, Date dataCriacao, byte[] foto, Boolean isAnonimo, Boolean isModerador, Elo elo) {
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.foto = foto;
        this.isAnonimo = isAnonimo;
        this.isModerador = isModerador;
        this.elo = elo;
    }
}
