package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.entities.Usuario;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.Set;

@Mapper(componentModel = "spring", uses = EloMapper.class, imports = LocalDateTime.class)
public interface UsuarioMapper {
    @Named("UsuarioToDto")
    @Mapping(target = "elo", qualifiedByName = "EloToDto")
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    UsuarioDTO usuarioToDto(Usuario usuario);

    @Named("UsuariosToDtos")
    @IterableMapping(qualifiedByName = "UsuarioToDto")
    Set<UsuarioDTO> usuariosToDtoSet(Set<Usuario> usuarios);

    @Named("DtoToUsuario")
    @Mapping(target = "elo", qualifiedByName = "DtoToElo")
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    Usuario dtoToUsuario(UsuarioDTO dto);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    void dtoToUsuario(UsuarioDTO dto, @MappingTarget Usuario usuario);
}
