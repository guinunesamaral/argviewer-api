package com.argviewer.api.controllers;

import com.argviewer.domain.interfaces.mappers.ResponseMapper;
import com.argviewer.domain.interfaces.services.TagService;
import com.argviewer.domain.model.dtos.TagDTO;
import com.argviewer.domain.model.responses.FindTagResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;
    private final ResponseMapper responseMapper;

    public TagController(TagService tagService, ResponseMapper responseMapper) {
        this.tagService = tagService;
        this.responseMapper = responseMapper;
    }

    @GetMapping
    public List<FindTagResponse> find() {
        List<TagDTO> dtoList = tagService.find();
        return responseMapper.dtoListToFindTagResponseList(dtoList);
    }
}
