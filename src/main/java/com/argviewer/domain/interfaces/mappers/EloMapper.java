package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.EloDTO;
import com.argviewer.domain.model.entities.Elo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EloMapper {
    @Named("EloToDto")
    @Mapping(target = "usuarios", ignore = true)
    EloDTO eloToDto(Elo elo);

    @Named("DtoToElo")
    @Mapping(target = "usuarios", ignore = true)
    Elo dtoToElo(EloDTO dto);
}
