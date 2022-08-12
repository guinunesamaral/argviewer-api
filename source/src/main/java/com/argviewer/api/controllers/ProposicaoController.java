package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Proposicao")
public class ProposicaoController {

    @Autowired
    private ProposicaoService proposicaoService;

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody ProposicaoDTO request) {
        int id = proposicaoService.create(request);
        return ResponseEntity.ok(id);
    }

    @PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody ProposicaoDTO request) {
        proposicaoService.update(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposicaoDTO> findById(@PathVariable int id) {
        ProposicaoDTO dto = proposicaoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProposicaoDTO>> findAll(@RequestParam(required = false) Integer idUsuario) {
        List<ProposicaoDTO> dtoList = idUsuario == null
                ? proposicaoService.findAll(null)
                : proposicaoService.findAll(idUsuario);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> count(@RequestParam(required = false) Integer idUsuario) {
        long qtdProposicoes = idUsuario == null
                ? proposicaoService.count(null)
                : proposicaoService.count(idUsuario);
        return ResponseEntity.ok(qtdProposicoes);
    }
}
