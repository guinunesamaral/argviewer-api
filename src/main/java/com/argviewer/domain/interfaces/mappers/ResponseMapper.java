package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.EloDTO;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.responses.CreateUsuarioResponse;
import com.argviewer.domain.model.responses.FindEloResponse;
import com.argviewer.domain.model.responses.FindProposicaoResponse;
import com.argviewer.domain.model.responses.FindUsuarioResponse;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;
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

    @Named("DtoSetToFindUsuarioResponseSet")
    @IterableMapping(qualifiedByName = "DtoToFindUsuarioResponse")
    Set<FindUsuarioResponse> dtoSetToFindUsuarioResponseSet(Set<UsuarioDTO> dtoSet);

    @Named("DtoToFindProposicaoResponse")
    @Mapping(target = "usuario", ignore = true)
    FindProposicaoResponse dtoToFindProposicaoResponse(ProposicaoDTO dto);

    @Named("DtoSetToFindProposicaoResponseSet")
    @IterableMapping(qualifiedByName = "DtoToFindProposicaoResponse")
    Set<FindProposicaoResponse> dtoSetToFindProposicaoResponseSet(Set<ProposicaoDTO> dtoSet);
}
