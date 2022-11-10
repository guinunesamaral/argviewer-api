package com.argviewer.domain.interfaces.mappers;

import com.argviewer.domain.model.dtos.TagDTO;
import com.argviewer.domain.model.entities.Tag;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Named("TagToDto")
    @Mapping(target = "proposicoes", ignore = true)
    TagDTO tagToDto(Tag tag);

    @Named("TagsToDtoList")
    @IterableMapping(qualifiedByName = "TagToDto")
    List<TagDTO> tagsToDtoList(List<Tag> tags);

    @Named("DtoToTag")
    @Mapping(target = "proposicoes", ignore = true)
    Tag dtoToTag(TagDTO dto);

    @Named("DtosToTagList")
    @IterableMapping(qualifiedByName = "DtoToTag")
    List<Tag> dtosToTagList(List<TagDTO> dtos);
}
