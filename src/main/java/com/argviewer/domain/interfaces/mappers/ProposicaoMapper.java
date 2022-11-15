package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.dtos.UsuarioVoteDTO;
import com.argviewer.domain.model.dtos.UsuarioVoteIdDTO;
import com.argviewer.domain.model.entities.Proposicao;
import com.argviewer.domain.model.entities.UsuarioVote;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", imports =  UsuarioVoteIdDTO.class, uses = {UsuarioMapper.class, TagMapper.class})
public interface ProposicaoMapper {
    @Named("UsuarioVoteToDto")
    @Mapping(target = "id", expression = "java(new UsuarioVoteIdDTO(usuarioVote.getId().getUsuarioId(), usuarioVote.getId().getProposicaoId()))")
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "proposicao", ignore = true)
    UsuarioVoteDTO usuarioVoteToDto(UsuarioVote usuarioVote);

    @Named("VotesToDtoList")
    @IterableMapping(qualifiedByName = "UsuarioVoteToDto")
    List<UsuarioVoteDTO> votesToDtoList(List<UsuarioVote> votes);

    @Named("ProposicaoToDto")
    @Mapping(target = "usuario", qualifiedByName = "UsuarioToDto")
    @Mapping(target = "tags", qualifiedByName = "TagsToDtoList")
    @Mapping(target = "votes", qualifiedByName = "VotesToDtoList")
    ProposicaoDTO proposicaoToDto(Proposicao proposicao);

    @IterableMapping(qualifiedByName = "ProposicaoToDto")
    List<ProposicaoDTO> proposicoesToDtoList(List<Proposicao> proposicoes);

    @Named("DtoToProposicao")
    @Mapping(target = "usuario", qualifiedByName = "DtoToUsuario")
    @Mapping(target = "tags", qualifiedByName = "DtosToTagList")
    @Mapping(target = "respostas", ignore = true)
    @Mapping(target = "votes", ignore = true)
    Proposicao dtoToProposicao(ProposicaoDTO dto);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "qtdUpvotes", ignore = true)
    @Mapping(target = "qtdDownvotes", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "isRespostaContraria", ignore = true)
    @Mapping(target = "proposicaoInicial", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "respostas", ignore = true)
    @Mapping(target = "votes", ignore = true)
    void dtoToProposicao(ProposicaoDTO dto, @MappingTarget Proposicao proposicao);
}
