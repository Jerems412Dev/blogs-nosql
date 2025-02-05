package com.jeremsdev.blogs.dto;

import com.jeremsdev.blogs.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDTO {
    private String idBlog;
    private String title;
    private String description;
    private String content;
    private String publicationDate;
    private String author;
    private Category category;
}
