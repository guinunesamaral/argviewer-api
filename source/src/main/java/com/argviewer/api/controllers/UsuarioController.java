package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.business.UsuarioService;
import com.argviewer.domain.model.requests.user.SaveRequest;
import com.argviewer.domain.model.responses.user.SaveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.argviewer.domain.model.responses.user.FindAllResponse;
import com.argviewer.domain.model.responses.user.FindByIdResponse;

@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioBusiness;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveResponse> save(@RequestBody SaveRequest request) {
        return ResponseEntity.ok(
                new SaveResponse(this.usuarioBusiness.save(request.usuario()))
        );
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindByIdResponse> findById(@PathVariable int id) {
        return ResponseEntity.ok(
                new FindByIdResponse(
                        usuarioBusiness.findById(id)));
    }

    @GetMapping("/")
    public ResponseEntity<FindAllResponse> findAll() {
        return ResponseEntity.ok(
                new FindAllResponse(usuarioBusiness.findAll()));
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(usuarioBusiness.count());
    }
}