package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.entities.Usuario;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface UsuarioMapper {
    @Named("UsuarioToDto")
    @Mapping(target = "proposicoesCriadas", ignore = true)
    UsuarioDTO usuarioToDto(Usuario usuario);

    @Named("UsuariosToDtos")
    @IterableMapping(qualifiedByName = "UsuarioToDto")
    List<UsuarioDTO> usuariosToDtoList(List<Usuario> usuarios);

    @Named("DtoToUsuario")
    @Mapping(target = "proposicoesCriadas", ignore = true)
    Usuario dtoToUsuario(UsuarioDTO dto);

    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "votes", ignore = true)
    void dtoToUsuario(UsuarioDTO dto, @MappingTarget Usuario usuario);
}
