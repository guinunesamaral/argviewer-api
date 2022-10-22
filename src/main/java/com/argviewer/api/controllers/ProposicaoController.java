package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.mappers.RequestMapper;
import com.argviewer.domain.interfaces.mappers.ResponseMapper;
import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
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
    public Set<FindProposicaoResponse> find(@RequestParam(required = false) Integer usuarioId, @RequestParam(required = false) Integer tagId) {
        Set<ProposicaoDTO> dtos = proposicaoService.find(usuarioId, tagId);
        return responseMapper.dtosToFindProposicaoResponseSet(dtos);
    }

    @GetMapping("/texto")
    public Set<FindProposicaoResponse> findByTextoContaining(@RequestParam String value) {
        Set<ProposicaoDTO> dtos = proposicaoService.findByTextoContaining(value);
        return responseMapper.dtosToFindProposicaoResponseSet(dtos);
    }

    @GetMapping("/{proposicaoId}")
    public FindProposicaoResponse findById(@PathVariable int proposicaoId) {
        ProposicaoDTO dto = proposicaoService
                .findById(proposicaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário existe para esse id."));
        return responseMapper.dtoToFindProposicaoResponse(dto);
    }

    @GetMapping("/{proposicaoId}/respostas")
    public Set<FindProposicaoResponse> findRespostas(@PathVariable int proposicaoId) {
        Set<ProposicaoDTO> dtoSet = proposicaoService.findRespostas(proposicaoId);
        return responseMapper.dtosToFindProposicaoResponseSet(dtoSet);
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

    @PostMapping("/replica")
    public ResponseEntity<Response> addReplica(@RequestParam int proposicaoId, @RequestParam int replicaId) throws IllegalOperationException {
        boolean saved = proposicaoService.saveRespostas(proposicaoId, replicaId);
        Response response = new Response(200, String.format("Replica %s com sucesso.", saved ? "adicionada" : "removida"), System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{proposicaoId}")
    public ResponseEntity<Response> deleteById(@PathVariable int proposicaoId) {
        proposicaoService.deleteById(proposicaoId);
        Response response = new Response(200, "Proposição deletada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
}
