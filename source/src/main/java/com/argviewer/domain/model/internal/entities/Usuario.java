package com.argviewer.domain.model.internal.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UQ_Usuario_Email", columnNames = "email"),
        @UniqueConstraint(name = "UQ_Usuario_Nickname", columnNames = "nickname")
})
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

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 128)
    private String senha;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    private LocalDateTime dataAlteracao;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] foto;

    @Column(nullable = false)
    private Boolean isAnonimo;

    @Column(nullable = false)
    private Boolean isModerador;

    @ManyToOne
    @JoinColumn(name = "elo_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Usuario_Elo"))
    private Elo elo;

    @OneToMany(mappedBy = "usuario")
    private List<Historico> historicos;

    @OneToMany(mappedBy = "usuario")
    private List<Proposicao> proposicoesCriadas;

    @ManyToMany()
    @JoinTable(
            name = "usuario_segue_proposicao",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "proposicao_id", foreignKey = @ForeignKey(name = "FK_Proposicao_Seguidor")))
    private List<Proposicao> proposicoesSeguindo;

    @ManyToMany()
    @JoinTable(
            name = "usuario_tem_seguidor",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "seguidor_id", foreignKey = @ForeignKey(name = "FK_Usuario_Seguidor")))
    private List<Usuario> seguidores;

    @ManyToMany(mappedBy = "seguidores")
    private List<Usuario> seguindo;

    public Usuario() {
    }

    public Usuario(String nome, String nickname, String email, String senha, LocalDateTime dataCriacao, byte[] foto, Boolean isAnonimo, Boolean isModerador, Elo elo) {
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

    public Usuario(Integer id, String nome, String nickname, String email, String senha, LocalDateTime dataCriacao, byte[] foto, Boolean isAnonimo, Boolean isModerador, Elo elo) {
        this(nome, nickname, email, senha, dataCriacao, foto, isAnonimo, isModerador, elo);
        this.id = id;
    }
}
