package com.jeremsdev.blogs.repository;

import com.jeremsdev.blogs.model.BlogIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BlogSearchRepository extends ElasticsearchRepository<BlogIndex, String> {
    List<BlogIndex> findByTitleContaining(String keyword);
    List<BlogIndex> findByDescriptionContaining(String keyword);
    List<BlogIndex> findByAuthorContaining(String keyword);
    List<BlogIndex> findByTitleContainingOrDescriptionContainingOrContentContaining(String titreContaining, String descriptionContaining, String contentContaining);
}
