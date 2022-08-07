package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.internal.dtos.HistoricoDTO;
import com.argviewer.domain.model.internal.entities.Historico;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, TipoAcaoMapper.class})
public interface HistoricoMapper {

    @Named("HistoricoToDTO")
    @Mapping(target = "tipoAcao", qualifiedByName = "TipoAcaoToDTO")
    @Mapping(target = "usuario", qualifiedByName = "UsuarioToDTO")
    HistoricoDTO historicoToDTO(Historico historico);

    @Named("HistoricosToDtoList")
    @IterableMapping(qualifiedByName = "HistoricoToDTO")
    List<HistoricoDTO> historicosToDtoList(List<Historico> historicos);
}
