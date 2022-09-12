package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.TagDTO;

import java.util.Set;

public interface TagService {

    Set<TagDTO> find();
}
