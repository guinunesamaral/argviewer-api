package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.services.UsuarioService;
import com.argviewer.domain.interfaces.mapper.RequestMapper;
import com.argviewer.domain.model.external.UsuarioExternal;
import com.argviewer.domain.model.external.requests.usuario.SaveUsuarioRequest;
import com.argviewer.domain.model.external.responses.user.SaveUsuarioResponse;
import com.argviewer.domain.model.internal.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.argviewer.domain.model.external.responses.user.FindAllResponse;
import com.argviewer.domain.model.external.responses.user.FindUsuarioByIdResponse;

import java.util.List;

@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioBusiness;

    @Autowired
    private RequestMapper requestMapper;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveUsuarioResponse> save(@RequestBody SaveUsuarioRequest request) {
        UsuarioDTO usuarioDTO = requestMapper.usuarioExternalToDTO(request);
        int id = usuarioBusiness.save(usuarioDTO);
        return ResponseEntity.ok(
                new SaveUsuarioResponse(
                        id
                ));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindUsuarioByIdResponse> findById(@PathVariable int id) {
        UsuarioDTO usuarioDTO = usuarioBusiness.findById(id);
        UsuarioExternal usuarioExternal = requestMapper.dtoToUsuarioExternal(usuarioDTO);
        return ResponseEntity.ok(
                new FindUsuarioByIdResponse(
                        usuarioExternal
                )
        );
    }

    @GetMapping("/")
    public ResponseEntity<FindAllResponse> findAll() {
        List<UsuarioDTO> usuarioDtoList = usuarioBusiness.findAll();
        List<UsuarioExternal> usuarioExternalList = requestMapper.dtosToUsuarioExternalList(usuarioDtoList);
        return ResponseEntity.ok(
                new FindAllResponse(
                        usuarioExternalList
                ));
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(usuarioBusiness.count());
    }
}