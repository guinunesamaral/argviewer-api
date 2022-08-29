package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.entities.Proposicao;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface ProposicaoMapper {
    @Named("ProposicaoToDto")
    @Mapping(target = "usuario", qualifiedByName = "UsuarioToDto")
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "respostas", ignore = true)
    ProposicaoDTO proposicaoToDto(Proposicao proposicao);

    @IterableMapping(qualifiedByName = "ProposicaoToDto")
    Set<ProposicaoDTO> proposicoesToDtoSet(Set<Proposicao> proposicoes);

    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "usuario", qualifiedByName = "DtoToUsuario")
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "respostas", ignore = true)
    Proposicao dtoToProposicao(ProposicaoDTO dto);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataAlteracao", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void dtoToProposicao(ProposicaoDTO dto, @MappingTarget Proposicao proposicao);
}
