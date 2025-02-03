package com.jeremsdev.blogs.mapper;

import com.jeremsdev.blogs.dto.BlogDTO;
import com.jeremsdev.blogs.model.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogMapper {
    BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);
    BlogDTO toDTO(Blog blog);
    Blog toEntity(BlogDTO blogDTO);

    void updateEntityFromDTO(BlogDTO blogDTO, @MappingTarget Blog blog);
}
