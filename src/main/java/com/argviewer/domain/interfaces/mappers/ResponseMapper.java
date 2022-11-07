package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.EloDTO;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.dtos.TagDTO;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.responses.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", imports = Base64.class)
public interface ResponseMapper {

    CreateUsuarioResponse idToCreateUsuarioResponse(Integer id);

    @Named("DtoToFindUsuarioResponse")
    @Mapping(target = "foto", expression = "java(dto.getFoto() != null ? new String(Base64.getUrlDecoder().decode(dto.getFoto())) : null)")
    @Mapping(target = "elo", qualifiedByName = "DtoToFindEloResponse")
    FindUsuarioResponse dtoToFindUsuarioResponse(UsuarioDTO dto);

    @Named("DtoToFindEloResponse")
    FindEloResponse dtoToFindEloResponse(EloDTO dto);

    @Named("DtosToFindUsuarioResponseSet")
    @IterableMapping(qualifiedByName = "DtoToFindUsuarioResponse")
    Set<FindUsuarioResponse> dtosToFindUsuarioResponseSet(Set<UsuarioDTO> dtos);

    @Named("DtoToFindTagResponse")
    FindTagResponse dtoToFindTagResponse(TagDTO dto);

    @Named("DtosToFindTagResponseSet")
    @IterableMapping(qualifiedByName = "DtoToFindTagResponse")
    Set<FindTagResponse> dtoSetToFindTagResponseSet(Set<TagDTO> dtos);

    @Named("DtoToFindProposicaoResponse")
    @Mapping(target = "usuario", qualifiedByName = "DtoToFindUsuarioResponse")
    @Mapping(target = "tags", qualifiedByName = "DtosToFindTagResponseSet")
    FindProposicaoResponse dtoToFindProposicaoResponse(ProposicaoDTO dto);

    @Named("DtosToFindProposicaoResponseSet")
    @IterableMapping(qualifiedByName = "DtoToFindProposicaoResponse")
    List<FindProposicaoResponse> dtosToFindProposicaoResponseSet(List<ProposicaoDTO> dtos);
}
