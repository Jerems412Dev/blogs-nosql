package com.jeremsdev.blogs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "blogs")
public class BlogIndex {
    @Id
    private String idBlog;
    private String title;
    private String description;
    private String content;
    private String publicationDate;
    private String author;
    private Category category;
}
