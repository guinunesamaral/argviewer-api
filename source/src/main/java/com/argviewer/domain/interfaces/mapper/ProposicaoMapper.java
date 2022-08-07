package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.internal.dtos.ProposicaoDTO;
import com.argviewer.domain.model.internal.dtos.UsuarioDTO;
import com.argviewer.domain.model.internal.entities.Proposicao;
import com.argviewer.domain.model.internal.entities.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = UsuarioMapper.class)
public interface ProposicaoMapper {
    @Named("ProposicaoToDTO")
    @Mapping(target = "usuario", qualifiedByName = "UsuarioToDTO")
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "respostas", ignore = true)
    ProposicaoDTO proposicaoToDTO(Proposicao proposicao);

    @IterableMapping(qualifiedByName = "UsuarioToDTO")
    List<UsuarioDTO> seguidoresToDtoList(List<Usuario> usuarios);

    @IterableMapping(qualifiedByName = "ProposicaoToDTO")
    List<ProposicaoDTO> proposicoesToDtoList(List<Proposicao> proposicoes);
}
