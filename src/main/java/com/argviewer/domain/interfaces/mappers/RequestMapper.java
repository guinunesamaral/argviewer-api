package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.dtos.TagDTO;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.dtos.UsuarioVoteDTO;
import com.argviewer.domain.model.requests.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {Arrays.class, Collectors.class, BCryptPasswordEncoder.class, LocalDateTime.class, Base64.class, TagDTO.class})
public interface RequestMapper {
    @Mapping(target = "senha", expression = "java(new BCryptPasswordEncoder().encode(request.getSenha()))")
    @Mapping(target = "dataCriacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "active", expression = "java(true)")
    @Mapping(target = "foto", expression = "java(request.getFoto() != null ? Base64.getEncoder().encode(request.getFoto().getBytes()) : null)")
    UsuarioDTO createUsuarioRequestToDto(CreateUsuarioRequest request);

    @Mapping(target = "senha", expression = "java(new BCryptPasswordEncoder().encode(request.getSenha()))")
    @Mapping(target = "dataAlteracao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "foto", expression = "java(request.getFoto() != null ? Base64.getEncoder().encode(request.getFoto().getBytes()) : null)")
    UsuarioDTO updateUsuarioRequestToDto(UpdateUsuarioRequest request);

    @Mapping(target = "dataCriacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "usuario", expression = "java(new UsuarioDTO(request.getUsuarioId()))")
    @Mapping(target = "proposicaoInicial", expression = "java(true)")
    ProposicaoDTO createProposicaoRequestToDto(CreateProposicaoRequest request);

    @Mapping(target = "dataCriacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "usuario", expression = "java(new UsuarioDTO(request.getUsuarioId()))")
    @Mapping(target = "isRespostaFavoravel", expression = "java(request.isRespostaFavoravel())")
    ProposicaoDTO addRespostaRequestToDto(AddRespostaRequest request);

    @Mapping(target = "dataAlteracao", expression = "java(LocalDateTime.now())")
    ProposicaoDTO updateProposicaoRequestToDto(UpdateProposicaoRequest request);

    @Mapping(target = "usuario", expression = "java(new UsuarioDTO(request.getUsuarioId()))")
    @Mapping(target = "proposicao", expression = "java(new ProposicaoDTO(request.getProposicaoId()))")
    UsuarioVoteDTO addVoteRequestToDto(AddVoteRequest request);

    @Mapping(target = "usuario", expression = "java(new UsuarioDTO(request.getUsuarioId()))")
    @Mapping(target = "proposicao", expression = "java(new ProposicaoDTO(request.getProposicaoId()))")
    UsuarioVoteDTO removeVoteRequestToDto(RemoveVoteRequest request);
}
