package com.argviewer.api.controllers;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argviewer.domain.model.dtos.ProposicaoDto;
import com.argviewer.domain.model.requests.propositions.FindAllRequest;

@RestController
@RequestMapping("/api/Proposition")
public class ProposicaoController {

    @GetMapping("/")
    public ResponseEntity<FindAllRequest> getAll() {
        return ResponseEntity
                .ok(new FindAllRequest(List.of(new ProposicaoDto(1, "some text", "fonte", Date.from(Instant.now()),
                        Date.from(Instant.now()), 0, 0, 0, 0, 1))));
    }
}
