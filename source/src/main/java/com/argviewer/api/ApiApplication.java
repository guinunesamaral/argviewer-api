package com.argviewer.api;

import com.argviewer.domain.interfaces.repository.EloRepository;
import com.argviewer.domain.interfaces.repository.UsuarioRepository;
import com.argviewer.domain.model.entities.Elo;
import com.argviewer.domain.model.entities.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@SpringBootApplication
@ComponentScan("com.argviewer.*")
@EntityScan("com.argviewer.*")
@EnableJpaRepositories(basePackages = "com.argviewer.domain.interfaces")
public class ApiApplication {

	private static final Logger log = LoggerFactory.getLogger(ApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, EloRepository eloRepository) {
		return args -> {
			Elo elo = eloRepository.findById(1).get();

			Usuario usuario = usuarioRepository.save(
					new Usuario(
							"guilherme",
							"gnunes",
							"g@email.com",
							"senha",
							Date.from(Instant.now()),
							"foto".getBytes(StandardCharsets.UTF_8),
							false,
							false,
							elo
					));

			log.info(usuario.toString());
		};
	}
}
