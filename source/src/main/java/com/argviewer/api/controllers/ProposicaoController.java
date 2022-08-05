package com.argviewer.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Proposicao")
public class ProposicaoController {

//    @Autowired
//    private ProposicaoServiceImpl proposicaoBusiness;

//    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<SaveResponse> save(@RequestBody SaveRequest request) {
//        return ResponseEntity.ok(
//                new SaveResponse(proposicaoBusiness.save(request.usuario()))
//        );
//    }

    public void findProposicoesByUsuarioId(int id) { }
}
