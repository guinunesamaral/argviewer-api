package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.internal.dtos.ProposicaoDTO;
import com.argviewer.domain.model.internal.dtos.UsuarioDTO;
import com.argviewer.domain.model.internal.entities.Proposicao;
import com.argviewer.domain.model.internal.entities.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, EloMapper.class})
public interface ProposicaoMapper {
    @Named("ProposicaoToDTO")
    @Mapping(target = "usuario", qualifiedByName = "UsuarioToDTO")
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "respostas", ignore = true)
    ProposicaoDTO proposicaoToDTO(Proposicao proposicao);

    @Named("SeguidoresToDTO")
    @IterableMapping(qualifiedByName = "UsuarioToDTO")
    List<UsuarioDTO> seguidoresToDTO(List<Usuario> usuarios);

    @Named("proposicoesToProposicaoDtoList")
    @IterableMapping(qualifiedByName = "ProposicaoToDTO")
    List<ProposicaoDTO> proposicoesToProposicaoDtoList(List<Proposicao> proposicoes);
}
