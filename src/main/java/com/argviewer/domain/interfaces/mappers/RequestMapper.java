package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.EloDTO;
import com.argviewer.domain.model.dtos.ProposicaoDTO;
import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.requests.CreateProposicaoRequest;
import com.argviewer.domain.model.requests.CreateUsuarioRequest;
import com.argviewer.domain.model.requests.UpdateProposicaoRequest;
import com.argviewer.domain.model.requests.UpdateUsuarioRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Base64;

@Mapper(componentModel = "spring", imports = {BCryptPasswordEncoder.class, LocalDateTime.class, Base64.class, EloDTO.class})
public interface RequestMapper {
    @Mapping(target = "senha", expression = "java(new BCryptPasswordEncoder().encode(request.getSenha()))")
    @Mapping(target = "dataCriacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "active", expression = "java(true)")
    @Mapping(target = "foto", expression = "java(request.getFoto() != null ? Base64.getEncoder().encode(request.getFoto().getBytes()) : null)")
    @Mapping(target = "elo", expression = "java(new EloDTO(1))")
    UsuarioDTO createUsuarioRequestToDto(CreateUsuarioRequest request);

    @Mapping(target = "senha", expression = "java(new BCryptPasswordEncoder().encode(request.getSenha()))")
    @Mapping(target = "dataAlteracao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "foto", expression = "java(request.getFoto() != null ? Base64.getEncoder().encode(request.getFoto().getBytes()) : null)")
    @Mapping(target = "elo", expression = "java(new EloDTO(request.getEloId()))")
    UsuarioDTO updateUsuarioRequestToDto(UpdateUsuarioRequest request);

    @Mapping(target = "dataCriacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "usuario", expression = "java(new UsuarioDTO(request.getUsuarioId()))")
    ProposicaoDTO createProposicaoRequestToDto(CreateProposicaoRequest request);

    @Mapping(target = "dataAlteracao", expression = "java(LocalDateTime.now())")
    ProposicaoDTO updateProposicaoRequestToDto(UpdateProposicaoRequest request);
}
