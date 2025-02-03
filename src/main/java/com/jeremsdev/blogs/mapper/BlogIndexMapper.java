package com.jeremsdev.blogs.mapper;

import com.jeremsdev.blogs.dto.BlogDTO;
import com.jeremsdev.blogs.model.BlogIndex;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogIndexMapper {
    BlogIndexMapper INSTANCE = Mappers.getMapper(BlogIndexMapper.class);
    BlogDTO toDTO(BlogIndex blog);
    BlogIndex toEntity(BlogDTO blogDTO);

    void updateEntityFromDTO(BlogDTO blogDTO, @MappingTarget BlogIndex blog);
}
