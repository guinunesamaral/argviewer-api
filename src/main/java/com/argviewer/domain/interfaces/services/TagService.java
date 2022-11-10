package com.argviewer.domain.interfaces.services;

import com.argviewer.domain.model.dtos.TagDTO;

import java.util.List;

public interface TagService {

    List<TagDTO> find();
}
