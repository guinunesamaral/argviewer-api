package com.argviewer.services;

import com.argviewer.domain.interfaces.mappers.TagMapper;
import com.argviewer.domain.interfaces.repositories.TagRepository;
import com.argviewer.domain.interfaces.services.TagService;
import com.argviewer.domain.model.dtos.TagDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagDTO> find() {
        return tagMapper.tagsToDtoList(tagRepository.findAll());
    }
}
