package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.mapper.ExternalMapper;
import com.argviewer.domain.interfaces.services.UsuarioService;
import com.argviewer.domain.model.external.UsuarioExternal;
import com.argviewer.domain.model.external.requests.usuario.SaveUsuarioRequest;
import com.argviewer.domain.model.external.responses.usuario.CountUsuarioResponse;
import com.argviewer.domain.model.external.responses.usuario.SaveUsuarioResponse;
import com.argviewer.domain.model.internal.dtos.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.argviewer.domain.model.external.responses.usuario.FindAllUsuariosResponse;
import com.argviewer.domain.model.external.responses.usuario.FindUsuarioByIdResponse;

import java.util.List;

@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ExternalMapper externalMapper;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveUsuarioResponse> save(@RequestBody SaveUsuarioRequest request) {
        UsuarioDTO dto = externalMapper.usuarioExternalToDTO(request);
        int id = usuarioService.save(dto);
        return ResponseEntity.ok(
                new SaveUsuarioResponse(
                        id
                ));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindUsuarioByIdResponse> findById(@PathVariable int id) {
        UsuarioDTO dto = usuarioService.findById(id);
        UsuarioExternal usuario = externalMapper.dtoToUsuarioExternal(dto);
        return ResponseEntity.ok(
                new FindUsuarioByIdResponse(
                        usuario
                ));
    }

    @GetMapping("/")
    public ResponseEntity<FindAllUsuariosResponse> findAll() {
        List<UsuarioDTO> dtoList = usuarioService.findAll();
        List<UsuarioExternal> usuarioExternalList = externalMapper.dtosToUsuarioExternalList(dtoList);
        return ResponseEntity.ok(
                new FindAllUsuariosResponse(
                        usuarioExternalList
                ));
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CountUsuarioResponse> count() {
        long qtdUsuarios = usuarioService.count();
        return ResponseEntity.ok(new CountUsuarioResponse(qtdUsuarios));
    }
}