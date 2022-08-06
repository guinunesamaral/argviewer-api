package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.external.*;
import com.argviewer.domain.model.internal.dtos.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface ExternalMapper {
    ExternalMapper INSTANCE = Mappers.getMapper(ExternalMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "elo", ignore = true)
    @Mapping(target = "dataCriacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "dataAlteracao", expression = "java(null)")
    @Mapping(target = "foto", expression = "java(null)")
    @Mapping(target = "isAnonimo", expression = "java(false)")
    @Mapping(target = "isModerador", expression = "java(false)")
    @Mapping(target = "historicos", ignore = true)
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    UsuarioDTO usuarioExternalToDTO(UsuarioExternal usuarioExternal);

    @Named("DTOToUsuarioExternal")
    @Mapping(target = "elo", qualifiedByName = "DTOToEloExternal")
    @Mapping(target = "historicos", ignore = true)
    @Mapping(target = "proposicoesCriadas", ignore = true)
    @Mapping(target = "proposicoesSeguindo", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "seguindo", ignore = true)
    UsuarioExternal dtoToUsuarioExternal(UsuarioDTO dto);

    @IterableMapping(qualifiedByName = "DTOToUsuarioExternal")
    List<UsuarioExternal> dtosToUsuarioExternalList(List<UsuarioDTO> dto);

    @Named("EloExternalToDTO")
    @Mapping(target = "usuarios", ignore = true)
    EloDTO eloExternalToDTO(EloExternal eloExternal);

    @Named("DTOToEloExternal")
    @Mapping(target = "usuarios", ignore = true)
    EloExternal dtoToEloExternal(EloDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "seguidores", ignore = true)
    @Mapping(target = "respostas", ignore = true)
    @Mapping(target = "dataCriacao", expression = "java(LocalDateTime.now())")
    @Mapping(target = "dataAlteracao", ignore = true)
    ProposicaoDTO proposicaoExternalToDTO(ProposicaoExternal proposicaoExternal);

    @Mapping(target = "tipoAcao", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    HistoricoDTO historicoExternalToDTO(HistoricoExternal historicoExternal);

    @Mapping(target = "historicos", ignore = true)
    TipoAcaoDTO tipoAcaoExternalToDTO(TipoAcaoExternal tipoAcaoExternal);
}