package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.services.UsuarioService;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> create(@RequestBody UsuarioDTO request) {
        int id = usuarioService.create(request);
        return ResponseEntity.ok(id);
    }

    @PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody UsuarioDTO request) {
        usuarioService.update(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> findById(@PathVariable int id) {
        UsuarioDTO dto = usuarioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> dtoList = usuarioService.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> count() {
        long qtdUsuarios = usuarioService.count();
        return ResponseEntity.ok(qtdUsuarios);
    }

    @PatchMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> inactivate(@PathVariable int id) {
        usuarioService.inactivate(id);
        return ResponseEntity.ok().build();
    }
}