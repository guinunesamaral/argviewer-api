package com.argviewer.api;

import com.argviewer.domain.interfaces.repository.EloRepository;
import com.argviewer.domain.interfaces.repository.ProposicaoRepository;
import com.argviewer.domain.interfaces.repository.UsuarioRepository;
import com.argviewer.domain.model.internal.entities.Elo;
import com.argviewer.domain.model.internal.entities.Proposicao;
import com.argviewer.domain.model.internal.entities.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@SpringBootApplication
@ComponentScan("com.argviewer.*")
@EntityScan("com.argviewer.*")
@EnableJpaRepositories(basePackages = "com.argviewer.domain.interfaces")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, EloRepository eloRepository) {
        return args -> {

        };
    }

    public Elo saveElo(EloRepository eloRepository) {
        Elo elo = eloRepository.findByTitulo("elo1");
        if (elo == null)
            elo = eloRepository.save(
                    new Elo(
                            "elo1",
                            "iniciante"));
        return elo;
    }

    public Usuario saveUsuario(UsuarioRepository usuarioRepository, Elo elo) {

        Usuario usuario = usuarioRepository.findByEmail("gm@email.com");
        if (usuario == null)
            usuario = usuarioRepository.save(
                    new Usuario(
                            "guilherme",
                            "gnunes",
                            "gm@email.com",
                            "senha",
                            LocalDateTime.now(),
                            "foto".getBytes(StandardCharsets.UTF_8),
                            false,
                            false,
                            elo
                    ));
        return usuario;
    }

    public Proposicao saveProposicao(ProposicaoRepository proposicaoRepository, Usuario usuario) {
        Proposicao proposicao = proposicaoRepository.findByTexto("Primeira");
        if (proposicao == null)
            proposicao = proposicaoRepository.save(
                    new Proposicao(
                            "Primeira",
                            Date.from(Instant.now()),
                            0,
                            0,
                            0,
                            0,
                            usuario
                    )
            );
        return proposicao;
    }
}
