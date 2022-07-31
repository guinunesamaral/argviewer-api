package com.argviewer.api.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argviewer.domain.model.entities.User;
import com.argviewer.domain.model.responses.user.GetAllResponse;
import com.argviewer.domain.model.responses.user.GetByIdResponse;

@RestController
@RequestMapping("/api/Usuario")
public class UserController {

	@GetMapping("/")
	public ResponseEntity<GetAllResponse> getAll() {
		return ResponseEntity.ok(
				new GetAllResponse(
						List.of(
								new User(
										1,
										"g",
										"guinunesamaral",
										"g@email.com",
										"senha123",
										Date.from(Instant.now()),
										Date.from(Instant.now())))));
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetByIdResponse> getById(@PathVariable int id) {
		return ResponseEntity.ok(
				new GetByIdResponse(
						new User(
								1,
								"g",
								"guinunesamaral",
								"g@email.com",
								"senha123",
								Date.from(Instant.now()),
								Date.from(Instant.now()))));

	}

	@PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> create(User user) {
		return ResponseEntity.ok(user);
	}
}