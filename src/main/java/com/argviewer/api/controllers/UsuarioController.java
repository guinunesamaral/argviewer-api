package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.services.UsuarioService;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public ResponseEntity<Void> login(@RequestBody String emailNickname, @RequestBody String senha) {
        usuarioService.login(emailNickname, senha);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/")
    public ResponseEntity<Integer> create(@RequestBody UsuarioDTO dto) {
        int id = usuarioService.create(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Void> update(@RequestBody UsuarioDTO dto) throws IllegalOperationException {
        usuarioService.update(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable int id) {
        UsuarioDTO dto = usuarioService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(path = "/")
    public ResponseEntity<Set<UsuarioDTO>> findAll() {
        Set<UsuarioDTO> dtoList = usuarioService.findAll();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(path = "/count")
    public ResponseEntity<Long> count() {
        long qtdUsuarios = usuarioService.count();
        return ResponseEntity.ok(qtdUsuarios);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> inactivate(@PathVariable int id) {
        usuarioService.inactivate(id);
        return ResponseEntity.ok().build();
    }
}