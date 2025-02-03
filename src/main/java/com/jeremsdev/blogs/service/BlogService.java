package com.jeremsdev.blogs.service;

import com.jeremsdev.blogs.dto.BlogDTO;
import com.jeremsdev.blogs.model.Blog;
import com.jeremsdev.blogs.model.Category;

import java.util.List;

public interface BlogService {
    BlogDTO create(BlogDTO blogDTO);
    BlogDTO update(String idBlog, BlogDTO blogDTO);
    BlogDTO getById(String idBlog);
    List<BlogDTO> findAll();
    <T> List<BlogDTO> findByField(T field, T content);
    <T> List<BlogDTO> findByFieldIndex(T field, T content);
    List<BlogDTO> findByContain(String titre, String description, String content);
    void delete(String idBlog);
}
