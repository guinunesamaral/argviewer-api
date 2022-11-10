package com.argviewer.domain.model.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FindUsuarioResponse {
    private int id;
    private String nome;
    private String nickname;
    private String email;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private String foto;
    private boolean isActive;
    private boolean isAnonimo;
}