package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.internal.dtos.TipoAcaoDTO;
import com.argviewer.domain.model.internal.entities.TipoAcao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoAcaoMapper {
    @Named("TipoAcaoToDTO")
    @Mapping(target = "historicos", ignore = true)
    TipoAcaoDTO tipoAcaoToDTO(TipoAcao tipoAcao);
}
