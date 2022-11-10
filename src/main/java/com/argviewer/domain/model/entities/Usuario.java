package com.argviewer.domain.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UQ_Usuario_Email", columnNames = "email"),
        @UniqueConstraint(name = "UQ_Usuario_Nickname", columnNames = "nickname")
})
@Entity
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
    private boolean isActive;

    @Column(nullable = false)
    private boolean isAnonimo;

    @OneToMany(mappedBy = "usuario")
    private List<Proposicao> proposicoesCriadas;

    public Usuario() {
    }

    public Usuario(int id, String nome, String nickname, String email, String senha, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
    }
}
