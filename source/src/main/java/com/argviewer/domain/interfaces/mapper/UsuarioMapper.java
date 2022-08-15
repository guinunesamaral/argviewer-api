package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.dtos.UsuarioDTO;
import com.argviewer.domain.model.entities.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = EloMapper.class)
public interface UsuarioMapper {
    @Named("UsuarioToDTO")
    @Mapping(target = "elo", qualifiedByName = "EloToDTO")
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    UsuarioDTO usuarioToDTO(Usuario usuario);

    @Named("UsuariosToDtoList")
    @IterableMapping(qualifiedByName = "UsuarioToDTO")
    List<UsuarioDTO> usuariosToDtoList(List<Usuario> usuarios);

    @Named("DtoToUsuario")
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAlteracao", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "elo", ignore = true)
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    Usuario dtoToUsuario(UsuarioDTO dto);

    @InheritConfiguration
    void dtoToUsuario(UsuarioDTO dto, @MappingTarget Usuario usuario);
}
