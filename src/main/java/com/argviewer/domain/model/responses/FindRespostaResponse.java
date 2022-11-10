package com.argviewer.domain.model.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FindRespostaResponse {
    private int id;
    private String texto;
    private String fonte;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private int qtdUpvotes;
    private int qtdDownvotes;
    private Boolean isRespostaNegativa;
    private FindRespostaUsuarioResponse usuario;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class FindRespostaUsuarioResponse {
        private int id;
        private String nome;
        private String nickname;
        private String email;
        private boolean isActive;
        private boolean isAnonimo;
    }
}
