package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/Proposicao")
public class ProposicaoController {

    @Autowired
    private ProposicaoService proposicaoService;

    @PostMapping(path = "/")
    public ResponseEntity<Integer> create(@RequestBody ProposicaoDTO dto) {
        int id = proposicaoService.create(dto);
        return ResponseEntity.ok(id);
    }

    @PostMapping(path = "/{idProposicao}/resposta")
    public ResponseEntity<Integer> addResposta(@PathVariable int idProposicao, @RequestBody ProposicaoDTO dto) {
        int idResposta = proposicaoService.create(dto);
        proposicaoService.addResposta(idProposicao, idResposta);
        return ResponseEntity.ok(idResposta);
    }

    @PostMapping(path = "/{idProposicao}/seguidor")
    public ResponseEntity<Void> addSeguidor(@PathVariable int idProposicao, @RequestBody int idSeguidor) {
        proposicaoService.addSeguidor(idProposicao, idSeguidor);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/")
    public ResponseEntity<Void> update(@RequestBody ProposicaoDTO dto) {
        proposicaoService.update(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposicaoDTO> findById(@PathVariable int id) {
        ProposicaoDTO dto = proposicaoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ProposicaoDTO>> findAll(@RequestParam(required = false) Integer idUsuario) {
        Set<ProposicaoDTO> dtoList = idUsuario == null
                ? proposicaoService.findAll(null)
                : proposicaoService.findAll(idUsuario);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> count(@RequestParam(required = false) Integer idUsuario) {
        long qtdProposicoes = idUsuario == null
                ? proposicaoService.count(null)
                : proposicaoService.count(idUsuario);
        return ResponseEntity.ok(qtdProposicoes);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        proposicaoService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
