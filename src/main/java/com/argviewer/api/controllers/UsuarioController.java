package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.mappers.ResponseMapper;
import com.argviewer.domain.interfaces.mappers.RequestMapper;
import com.argviewer.domain.interfaces.services.UsuarioService;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
import com.argviewer.domain.model.requests.CreateUsuarioRequest;
import com.argviewer.domain.model.requests.LoginRequest;
import com.argviewer.domain.model.requests.UpdateUsuarioRequest;
import com.argviewer.domain.model.responses.FindUsuarioResponse;
import com.argviewer.domain.model.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ResponseMapper responseMapper;
    private final RequestMapper requestMapper;

    public UsuarioController(UsuarioService usuarioService, ResponseMapper responseMapper, RequestMapper requestMapper) {
        this.usuarioService = usuarioService;
        this.responseMapper = responseMapper;
        this.requestMapper = requestMapper;
    }

    @GetMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest request) {
        usuarioService.login(request.getNickname(), request.getSenha());
        Response response = new Response(200, "Usuário logado com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public Set<FindUsuarioResponse> find(@RequestParam(required = false) String value) {
        Set<UsuarioDTO> dtoSet = usuarioService.find(value);
        return responseMapper.dtosToFindUsuarioResponseSet(dtoSet);
    }

    @GetMapping("/{usuarioId}")
    public FindUsuarioResponse findById(@PathVariable int usuarioId) {
        UsuarioDTO dto = usuarioService
                .findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário existe para esse id."));
        return responseMapper.dtoToFindUsuarioResponse(dto);
    }

    @GetMapping("/{usuarioId}/seguidores")
    public Set<FindUsuarioResponse> findSeguidores(@PathVariable int usuarioId) {
        Set<UsuarioDTO> dtos = usuarioService.findSeguidores(usuarioId);
        return responseMapper.dtosToFindUsuarioResponseSet(dtos);
    }

    @GetMapping("/{usuarioId}/seguindo")
    public Set<FindUsuarioResponse> findSeguindo(@PathVariable int usuarioId) {
        Set<UsuarioDTO> dtos = usuarioService.findSeguindo(usuarioId);
        return responseMapper.dtosToFindUsuarioResponseSet(dtos);
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody CreateUsuarioRequest request) {
        int id = usuarioService.create(requestMapper.createUsuarioRequestToDto(request));
        URI location = URI.create("/usuarios/" + id);
        Response response = new Response(HttpStatus.CREATED.value(), "Usuário criado com sucesso.", System.currentTimeMillis());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping
    public ResponseEntity<Response> update(@RequestBody UpdateUsuarioRequest request) throws IllegalOperationException {
        usuarioService.update(requestMapper.updateUsuarioRequestToDto(request));
        Response response = new Response(200, "Usuário atualizado com sucesso", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inactivate")
    public ResponseEntity<Response> inactivate(@PathVariable int id) throws IllegalOperationException {
        usuarioService.inactivate(id);
        Response response = new Response(200, "O usuário foi inativado.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/seguidores")
    public ResponseEntity<Response> saveSeguidores(@PathVariable int id, @RequestParam int seguidorId) throws IllegalOperationException {
        boolean saved = usuarioService.saveSeguidores(id, seguidorId);
        Response response = new Response(200, String.format("Seguidor %s com sucesso.", saved ? "adicionado" : "removido"), System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/seguindo")
    public ResponseEntity<Response> saveSeguindo(@PathVariable int id, @RequestParam int seguindoId) throws IllegalOperationException {
        boolean saved = usuarioService.saveSeguidores(id, seguindoId);
        Response response = new Response(200, String.format("Seguindo %s.", saved ? "adicionado" : "removido"), System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
}