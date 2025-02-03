package com.jeremsdev.blogs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "blogs")
public class Blog {
    @Id
    private Long idBlog;
    private String title;
    private String description;
    private String content;
    private LocalDate publicationDate;
    private String author;
    private Category category;
}
