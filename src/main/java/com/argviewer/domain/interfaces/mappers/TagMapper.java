package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.TagDTO;
import com.argviewer.domain.model.entities.Tag;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Named("TagToDto")
    @Mapping(target = "proposicoes", ignore = true)
    TagDTO tagToDto(Tag tag);

    @Named("TagsToDtoSet")
    @IterableMapping(qualifiedByName = "TagToDto")
    Set<TagDTO> tagsToDtoSet(Set<Tag> tags);

    @Named("DtoToTag")
    @Mapping(target = "proposicoes", ignore = true)
    Tag dtoToTag(TagDTO dto);

    @Named("DtosToTagSet")
    @IterableMapping(qualifiedByName = "DtoToTag")
    Set<Tag> dtosToTagSet(Set<TagDTO> dtos);
}
