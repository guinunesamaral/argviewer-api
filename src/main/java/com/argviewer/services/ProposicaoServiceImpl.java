package com.argviewer.services;

import com.argviewer.domain.interfaces.mappers.ProposicaoMapper;
import com.argviewer.domain.interfaces.repositories.ProposicaoRepository;
import com.argviewer.domain.interfaces.services.ProposicaoService;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.entities.Proposicao;
import com.argviewer.domain.model.exceptions.EntityNotFoundException;
import com.argviewer.domain.model.exceptions.IllegalOperationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ProposicaoServiceImpl implements ProposicaoService {

    private final ProposicaoRepository proposicaoRepository;

    private final ProposicaoMapper proposicaoMapper;

    public ProposicaoServiceImpl(ProposicaoRepository proposicaoRepository, ProposicaoMapper proposicaoMapper) {
        this.proposicaoRepository = proposicaoRepository;
        this.proposicaoMapper = proposicaoMapper;
    }

    static Specification<Proposicao> belongsTo(int usuarioId) {
        return (proposicao, cq, cb) -> cb.equal(proposicao.get("usuario").get("id"), usuarioId);
    }

    static Specification<Proposicao> containsTag(int tagId) {
        return (proposicao, cq, cb) -> proposicao.join("tags").get("id").in(Set.of(tagId));
    }

    @Override
    public List<ProposicaoDTO> find(Integer usuarioId, Integer tagId) {
        List<Proposicao> proposicoes;

        if (usuarioId != null && tagId != null)
            proposicoes = proposicaoRepository.findAll(
                    where(belongsTo(usuarioId)).and(containsTag(tagId)))
                    .stream()
                    .filter(Proposicao::isProposicaoInicial)
                    .collect(Collectors.toList());
        else if (usuarioId != null)
            proposicoes = proposicaoRepository.findAll(where(belongsTo(usuarioId)))
                    .stream()
                    .filter(Proposicao::isProposicaoInicial)
                    .collect(Collectors.toList());
        else if (tagId != null)
            proposicoes = proposicaoRepository.findAll(containsTag(tagId));
        else
            proposicoes = proposicaoRepository.findAll();

        return proposicaoMapper.proposicoesToDtoList(proposicoes
                .stream()
                .sorted((p1, p2) -> Integer.compare(p2.getRespostas().size(), p1.getRespostas().size()))
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<ProposicaoDTO> findById(int id) {
        Optional<Proposicao> proposicao = proposicaoRepository.findById(id);
        return proposicao.map(proposicaoMapper::proposicaoToDto);
    }

    static Specification<Proposicao> containsTexto(String texto) {
        return (proposicao, cq, cb) -> proposicao.get("texto").in(texto);
    }

    @Override
    public List<ProposicaoDTO> findByTextoContaining(String value) {
        List<Proposicao> proposicoes = proposicaoRepository.findAll(where(containsTexto(value)));
        return proposicaoMapper.proposicoesToDtoList(proposicoes
                .stream()
                .sorted((p1, p2) -> Integer.compare(p2.getRespostas().size(), p1.getRespostas().size()))
                .collect(Collectors.toList()));
    }

    @Override
    public List<ProposicaoDTO> findRespostas(int proposicaoId) {
        Proposicao proposicao = proposicaoRepository
                .findById(proposicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));
        return proposicaoMapper.proposicoesToDtoList(proposicao.getRespostas()
                .stream()
                .sorted((p1, p2) -> p2.getDataCriacao().compareTo(p1.getDataCriacao()))
                .collect(Collectors.toList()));
    }

    @Override
    public int create(ProposicaoDTO dto) {
        Proposicao proposicao = proposicaoMapper.dtoToProposicao(dto);
        return proposicaoRepository.save(proposicao).getId();
    }

    @Override
    public void update(ProposicaoDTO dto) {
        Proposicao proposicao = proposicaoRepository
                .findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));
        proposicaoMapper.dtoToProposicao(dto, proposicao);
        proposicaoRepository.save(proposicao);
    }

    @Override
    public void addResposta(int proposicaoId, ProposicaoDTO dto) throws IllegalOperationException {
        Proposicao proposicao = proposicaoRepository
                .findById(proposicaoId)
                .orElseThrow(() -> new EntityNotFoundException("Proposição não encontrada."));

        Proposicao resposta = proposicaoMapper.dtoToProposicao(dto);
        proposicaoRepository.save(resposta);

        proposicao.getRespostas().add(resposta);
        proposicaoRepository.save(proposicao);
    }

    @Override
    public void deleteById(int proposicaoId) {
        proposicaoRepository.deleteById(proposicaoId);
    }
}
