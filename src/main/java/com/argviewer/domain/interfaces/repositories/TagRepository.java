package com.argviewer.domain.interfaces.repositories;

import com.argviewer.domain.model.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
