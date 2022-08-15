package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "usuario")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UQ_Usuario_Email", columnNames = "email"),
        @UniqueConstraint(name = "UQ_Usuario_Nickname", columnNames = "nickname")
})
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 128)
    private String senha;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime dataAlteracao;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] foto;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private boolean isAnonimo;

    @Column(nullable = false)
    private boolean isModerador;

    @ManyToOne
    @JoinColumn(name = "elo_id", nullable = false, foreignKey = @ForeignKey(name = "FK_Usuario_Elo"))
    private Elo elo;

    @OneToMany(mappedBy = "usuario")
    private List<Proposicao> proposicoesCriadas;

    @ManyToMany()
    @JoinTable(
            name = "proposicao_tem_seguidor",
            joinColumns = @JoinColumn(name = "seguidor_id", foreignKey = @ForeignKey(name = "FK_ProposicaoTemSeguidor_Seguidor")),
            inverseJoinColumns = @JoinColumn(name = "proposicao_id", foreignKey = @ForeignKey(name = "FK_ProposicaoTemSeguidor_Proposicao")))
    private List<Proposicao> proposicoesSeguindo;

    @ManyToMany()
    @JoinTable(
            name = "usuario_tem_seguidor",
            joinColumns = @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_UsuarioTemSeguidor_Usuario")),
            inverseJoinColumns = @JoinColumn(name = "seguidor_id", foreignKey = @ForeignKey(name = "FK_UsuarioTemSeguidor_Seguidor")))
    private List<Usuario> seguidores;

    @ManyToMany(mappedBy = "seguidores")
    private List<Usuario> seguindo;

    public Usuario() {
    }

    public Usuario(int id, String nome, String nickname, String email, String senha, byte[] foto, Elo elo) {
        this.id = id;
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.elo = elo;
    }
}
