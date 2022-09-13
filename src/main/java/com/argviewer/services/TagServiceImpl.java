package com.argviewer.services;

import com.argviewer.domain.interfaces.mappers.TagMapper;
import com.argviewer.domain.interfaces.repositories.TagRepository;
import com.argviewer.domain.interfaces.services.TagService;
import com.argviewer.domain.model.dtos.TagDTO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public Set<TagDTO> find() {
        return tagMapper.tagsToDtoSet(new HashSet<>(tagRepository.findAll()));
    }
}
