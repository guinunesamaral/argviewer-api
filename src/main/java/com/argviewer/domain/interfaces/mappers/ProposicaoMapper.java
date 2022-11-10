package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.entities.Proposicao;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, TagMapper.class})
public interface ProposicaoMapper {
    @Named("ProposicaoToDto")
    @Mapping(target = "usuario", qualifiedByName = "UsuarioToDto")
    @Mapping(target = "tags", qualifiedByName = "TagsToDtoList")
    ProposicaoDTO proposicaoToDto(Proposicao proposicao);

    @IterableMapping(qualifiedByName = "ProposicaoToDto")
    List<ProposicaoDTO> proposicoesToDtoList(List<Proposicao> proposicoes);

    @Mapping(target = "usuario", qualifiedByName = "DtoToUsuario")
    @Mapping(target = "tags", qualifiedByName = "DtosToTagList")
    Proposicao dtoToProposicao(ProposicaoDTO dto);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void dtoToProposicao(ProposicaoDTO dto, @MappingTarget Proposicao proposicao);
}
