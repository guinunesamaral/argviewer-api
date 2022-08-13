package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.dtos.TipoAcaoDTO;
import com.argviewer.domain.model.entities.TipoAcao;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TipoAcaoMapper {
    @Named("TipoAcaoToDTO")
    @Mapping(target = "historicos", ignore = true)
    TipoAcaoDTO tipoAcaoToDTO(TipoAcao tipoAcao);
}
