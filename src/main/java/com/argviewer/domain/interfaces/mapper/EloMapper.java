package com.argviewer.domain.interfaces.mapper;

import com.argviewer.domain.model.dtos.EloDTO;
import com.argviewer.domain.model.entities.Elo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EloMapper {
    @Named("EloToDTO")
    @Mapping(target = "usuarios", ignore = true)
    EloDTO eloToDTO(Elo elo);
}
