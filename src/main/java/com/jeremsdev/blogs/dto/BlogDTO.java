package com.jeremsdev.blogs.dto;

import com.jeremsdev.blogs.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDTO {
    private String idBlog;
    private String title;
    private String description;
    private String content;
    private LocalDate publicationDate;
    private String author;
    private boolean state;
    private Category category;
}
