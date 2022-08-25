package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.entities.Proposicao;
import com.argviewer.domain.model.entities.Usuario;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface ProposicaoMapper {
    @Named("ProposicaoToDTO")
    @Mapping(target = "usuario", qualifiedByName = "UsuarioToDTO")
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "respostas", ignore = true)
    ProposicaoDTO proposicaoToDTO(Proposicao proposicao);

    @IterableMapping(qualifiedByName = "UsuarioToDTO")
    Set<UsuarioDTO> seguidoresToDtoList(Set<Usuario> usuarios);

    @IterableMapping(qualifiedByName = "ProposicaoToDTO")
    Set<ProposicaoDTO> proposicoesToDtoList(Set<Proposicao> proposicoes);

    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "usuario", qualifiedByName = "DtoToUsuario")
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "respostas", ignore = true)
    Proposicao dtoToProposicao(ProposicaoDTO dto);

    @InheritConfiguration
    void dtoToProposicao(ProposicaoDTO dto, @MappingTarget Proposicao proposicao);
}