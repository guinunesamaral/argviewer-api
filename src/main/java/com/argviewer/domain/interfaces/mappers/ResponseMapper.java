package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.dtos.TagDTO;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.dtos.UsuarioVoteDTO;
import com.argviewer.domain.model.entities.UsuarioVote;
import com.argviewer.domain.model.responses.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring", imports = Base64.class)
public interface ResponseMapper {

    @Named("DtoToFindUsuarioResponse")
    @Mapping(target = "foto", expression = "java(dto.getFoto() != null ? new String(Base64.getUrlDecoder().decode(dto.getFoto())) : null)")
    FindUsuarioResponse dtoToFindUsuarioResponse(UsuarioDTO dto);

    @Named("DtosToFindUsuarioResponseList")
    @IterableMapping(qualifiedByName = "DtoToFindUsuarioResponse")
    List<FindUsuarioResponse> dtosToFindUsuarioResponseList(List<UsuarioDTO> dtos);

    @Named("DtoToFindTagResponse")
    FindTagResponse dtoToFindTagResponse(TagDTO dto);

    @Named("DtosToFindTagResponseList")
    @IterableMapping(qualifiedByName = "DtoToFindTagResponse")
    List<FindTagResponse> dtoListToFindTagResponseList(List<TagDTO> dtos);

    // Proposicao e suas respostas. Cada resposta com um usuário
    @Named("DtoToFindUsuarioRespostaResponse")
    @Mapping(target = "foto", expression = "java(dto.getFoto() != null ? new String(Base64.getUrlDecoder().decode(dto.getFoto())) : null)")
    FindRespostaResponse.FindRespostaUsuarioResponse dtoToFindUsuarioRespostaResponse(UsuarioDTO dto);

    @Named("DtoToFindRespostaResponse")
    @Mapping(target = "usuario", qualifiedByName = "DtoToFindUsuarioRespostaResponse")
    FindRespostaResponse dtoToFindRespostaResponse(ProposicaoDTO dto);

    @Named("DtosToFindRespostaResponseList")
    @IterableMapping(qualifiedByName = "DtoToFindRespostaResponse")
    List<FindRespostaResponse> dtosToFindRespostaResponseList(List<ProposicaoDTO> respostas);

    @Named("DtoToFindUsuarioVoteResponse")
    @Mapping(target = "usuarioId", expression = "java(dto.getId().getUsuarioId())")
    FindUsuarioVoteResponse dtoToFindUsuarioVoteResponse(UsuarioVoteDTO dto);

    @Named("DtoToFindUsuarioVoteResponseList")
    @IterableMapping(qualifiedByName = "DtoToFindUsuarioVoteResponse")
    List<FindUsuarioVoteResponse> dtoToFindUsuarioVoteResponseList(List<UsuarioVoteDTO> votes);

    @Named("DtoToFindProposicaoResponse")
    @Mapping(target = "respostas", qualifiedByName = "DtosToFindRespostaResponseList")
    @Mapping(target = "votes", qualifiedByName = "DtoToFindUsuarioVoteResponseList")
    FindProposicaoResponse dtoToFindProposicaoResponse(ProposicaoDTO dto);

    @Named("DtosToFindProposicaoResponseList")
    @IterableMapping(qualifiedByName = "DtoToFindProposicaoResponse")
    List<FindProposicaoResponse> dtosToFindProposicaoResponseList(List<ProposicaoDTO> dtos);
}
