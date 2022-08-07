package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.mapper.ExternalMapper;
import com.argviewer.domain.interfaces.mapper.ProposicaoMapper;
import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.external.ProposicaoExternal;
import com.argviewer.domain.model.external.requests.proposicao.SaveProposicaoRequest;
import com.argviewer.domain.model.external.responses.proposicao.FindAllProposicoesResponse;
import com.argviewer.domain.model.external.responses.proposicao.FindProposicaoByIdResponse;
import com.argviewer.domain.model.external.responses.proposicao.SaveProposicaoResponse;
import com.argviewer.domain.model.internal.dtos.ProposicaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Proposicao")
public class ProposicaoController {

    @Autowired
    private ProposicaoService proposicaoService;

    @Autowired
    private ProposicaoMapper proposicaoMapper;

    @Autowired
    private ExternalMapper externalMapper;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveProposicaoResponse> save(@RequestBody SaveProposicaoRequest request) {
        ProposicaoDTO dto = externalMapper.proposicaoExternalToDTO(request);
        return ResponseEntity.ok(new SaveProposicaoResponse(proposicaoService.save(dto)));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindProposicaoByIdResponse> findById(@PathVariable int id) {
        ProposicaoExternal proposicao = externalMapper.dtoToProposicaoExternal(proposicaoService.findById(id));
        return ResponseEntity.ok(new FindProposicaoByIdResponse(proposicao));
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindAllProposicoesResponse> findAll() {
        List<ProposicaoDTO> dtos = proposicaoService.findAll();
        List<ProposicaoExternal> proposicaoList = externalMapper.dtosToProposicaoExternalList(dtos);
        return ResponseEntity.ok(new FindAllProposicoesResponse(proposicaoList));
    }

    @GetMapping(path = "/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FindProposicaoByIdResponse> findByIdUsuario(@PathVariable int idUsuario) {
        ProposicaoExternal proposicao = externalMapper.dtoToProposicaoExternal(proposicaoService.findById(idUsuario));
        return ResponseEntity.ok(new FindProposicaoByIdResponse(proposicao));
    }
}
