package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.mappers.RequestMapper;
import com.argviewer.domain.interfaces.mappers.ResponseMapper;
import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
import com.argviewer.domain.model.exceptions.InvalidParameterException;
import com.argviewer.domain.model.requests.*;
import com.argviewer.domain.model.responses.FindProposicaoResponse;
import com.argviewer.domain.model.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

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
    public List<FindProposicaoResponse> find(@RequestParam(required = false) Integer usuarioId, @RequestParam(required = false) Integer tagId) {
        List<ProposicaoDTO> dtos = proposicaoService.find(usuarioId, tagId);
        return responseMapper.dtosToFindProposicaoResponseList(dtos);
    }

    @GetMapping("/texto")
    public List<FindProposicaoResponse> findByTextoContaining(@RequestParam String value) {
        List<ProposicaoDTO> dtos = proposicaoService.findByTextoContaining(value);
        return responseMapper.dtosToFindProposicaoResponseList(dtos);
    }

    @GetMapping("/{proposicaoId}")
    public FindProposicaoResponse findById(@PathVariable int proposicaoId) {
        ProposicaoDTO dto = proposicaoService
                .findById(proposicaoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum usuário existe para esse id."));
        return responseMapper.dtoToFindProposicaoResponse(dto);
    }

    @GetMapping("/{proposicaoId}/respostas")
    public List<FindProposicaoResponse> findRespostas(@PathVariable int proposicaoId) {
        List<ProposicaoDTO> dtoList = proposicaoService.findRespostas(proposicaoId);
        return responseMapper.dtosToFindProposicaoResponseList(dtoList);
    }

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody CreateProposicaoRequest request) throws InvalidParameterException {
        int id = proposicaoService.create(requestMapper.createProposicaoRequestToDto(request));
        URI location = URI.create("/proposicoes/" + id);
        Response response = new Response(HttpStatus.CREATED.value(), "Proposição criada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping
    public ResponseEntity<Response> update(@RequestBody UpdateProposicaoRequest request) throws InvalidParameterException {
        proposicaoService.update(requestMapper.updateProposicaoRequestToDto(request));
        Response response = new Response(200, "Proposição atualizada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @PostMapping("{proposicaoId}/resposta")
    public ResponseEntity<Response> addResposta(@PathVariable int proposicaoId, @RequestBody AddRespostaRequest request) throws IllegalOperationException {
        proposicaoService.addResposta(proposicaoId, requestMapper.addRespostaRequestToDto(request));
        Response response = new Response(200, "Resposta adicionada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/vote")
    public ResponseEntity<Response> addVote(@RequestBody AddVoteRequest request) throws InvalidParameterException {
        proposicaoService.addVote(requestMapper.addVoteRequestToDto(request));
        Response response = new Response(200, "Voto adicionado com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/vote")
    public ResponseEntity<Response> removeVote(@RequestBody RemoveVoteRequest request) throws InvalidParameterException {
        proposicaoService.removeVote(requestMapper.removeVoteRequestToDto(request));
        Response response = new Response(200, "Voto removido com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{proposicaoId}")
    public ResponseEntity<Response> deleteById(@PathVariable int proposicaoId) {
        proposicaoService.deleteById(proposicaoId);
        Response response = new Response(200, "Proposição deletada com sucesso.", System.currentTimeMillis());
        return ResponseEntity.ok(response);
    }
}
