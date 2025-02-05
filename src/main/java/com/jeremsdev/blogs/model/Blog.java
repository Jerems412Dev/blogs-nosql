package com.jeremsdev.blogs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "blogs")
public class Blog {
    @Id
    private String idBlog;
    private String title;
    private String description;
    private String content;
    private String publicationDate;
    private String author;
    private Category category;
}
