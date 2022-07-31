package com.argviewer.api.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argviewer.domain.model.entities.Proposicao;
import com.argviewer.domain.model.requests.propositions.GetAllRequest;

@RestController
@RequestMapping("/api/Proposition")
public class PropositionController {

    @GetMapping("/")
    public ResponseEntity<GetAllRequest> getAll() {
        return ResponseEntity
                .ok(new GetAllRequest(List.of(new Proposicao(1, "some text", "fonte", Date.from(Instant.now()),
                        Date.from(Instant.now()), 0, 0, 0, 0, 1))));
    }
}
