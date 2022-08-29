package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.mappers.RequestMapper;
import com.argviewer.domain.interfaces.mappers.ResponseMapper;
import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.requests.CreateProposicaoRequest;
import com.argviewer.domain.model.requests.UpdateProposicaoRequest;
import com.argviewer.domain.model.responses.FindProposicaoResponse;
import com.argviewer.domain.model.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Set;

@RestController
@RequestMapping("/api/proposicoes")
public class ProposicaoController {

    private final ProposicaoService proposicaoService;
    private final RequestMapper requestMapper;
    private final ResponseMapper responseMapper;

    public ProposicaoController(ProposicaoService proposicaoService, RequestMapper requestMapper, ResponseMapper responseMapper) {
        this.proposicaoService = proposicaoService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @GetMapping
    public Set<FindProposicaoResponse> find(@RequestParam(required = false) Integer idUsuario) {
        Set<ProposicaoDTO> dtoSet = proposicaoService.find(idUsuario);
        return responseMapper.dtoSetToFindProposicaoResponseSet(dtoSet);
    }

    @GetMapping("/{id}")
    public FindProposicaoResponse findById(@PathVariable int id) {
        ProposicaoDTO dto = proposicaoService
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário existe para esse id."));
        return responseMapper.dtoToFindProposicaoResponse(dto);
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody CreateProposicaoRequest request) {
        int id = proposicaoService.create(requestMapper.createProposicaoRequestToDto(request));
        URI location = URI.create("/proposicoes/" + id);
        Response response = new Response(HttpStatus.CREATED.value(), "Proposição criada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping
    public ResponseEntity<Response> update(@RequestBody UpdateProposicaoRequest request) {
        proposicaoService.update(requestMapper.updateProposicaoRequestToDto(request));
        Response response = new Response(200, "Proposição atualizada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{idProposicao}/resposta")
    public ResponseEntity<Response> addAnswer(@PathVariable int idProposicao, @RequestBody ProposicaoDTO dto) {
        int idResposta = proposicaoService.create(dto);
        proposicaoService.addAnswer(idProposicao, idResposta);
        URI location = URI.create("/proposicoes/" + idResposta);
        Response response = new Response(200, "Resposta adicionada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.created(location).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable int id) {
        proposicaoService.deleteById(id);
        Response response = new Response(200, "Proposição deletada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
}
