package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.internal.dtos.UsuarioDTO;
import com.argviewer.domain.model.internal.entities.Usuario;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = EloMapper.class)
public interface UsuarioMapper {
    @Named("UsuarioToDTO")
    @Mapping(target = "elo", qualifiedByName = "EloToDTO")
    @Mapping(target = "historicos", ignore = true)
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    UsuarioDTO usuarioToDTO(Usuario usuario);

    @Named("usuariosToUsuarioDtoList")
    @IterableMapping(qualifiedByName = "UsuarioToDTO")
    List<UsuarioDTO> usuariosToUsuarioDtoList(List<Usuario> usuarios);

    @Named("DTOToUsuario")
    @Mapping(target = "elo", ignore = true)
    @Mapping(target = "historicos", ignore = true)
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    Usuario dtoToUsuario(UsuarioDTO dto);
}
