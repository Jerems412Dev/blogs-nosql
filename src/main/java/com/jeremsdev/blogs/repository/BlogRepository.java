package com.jeremsdev.blogs.repository;

import com.jeremsdev.blogs.model.Blog;
import com.jeremsdev.blogs.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
    List<Blog> findByCategory(Category category);
    List<Blog> findByAuthor(String author);
    List<Blog> findByTitle(String title);
}
