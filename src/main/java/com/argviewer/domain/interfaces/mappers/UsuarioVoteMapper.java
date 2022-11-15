package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.UsuarioVoteDTO;
import com.argviewer.domain.model.entities.UsuarioVote;
import com.argviewer.domain.model.entities.UsuarioVoteId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        imports = UsuarioVoteId.class,
        uses = {UsuarioMapper.class, ProposicaoMapper.class})
public interface UsuarioVoteMapper {

    @Mapping(target = "id", expression = "java(new UsuarioVoteId(dto.getUsuario().getId(), dto.getProposicao().getId()))")
    @Mapping(target = "usuario", qualifiedByName = "DtoToUsuario")
    @Mapping(target = "proposicao", qualifiedByName = "DtoToProposicao")
    UsuarioVote dtoToUsuarioVote(UsuarioVoteDTO dto);
}
