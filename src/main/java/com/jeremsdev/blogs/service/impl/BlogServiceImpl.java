package com.jeremsdev.blogs.service.impl;

import com.jeremsdev.blogs.dto.BlogDTO;
import com.jeremsdev.blogs.exception.ResourceNotFoundException;
import com.jeremsdev.blogs.mapper.BlogMapper;
import com.jeremsdev.blogs.model.Blog;
import com.jeremsdev.blogs.model.Category;
import com.jeremsdev.blogs.repository.BlogRepository;
import com.jeremsdev.blogs.service.BlogService;
import com.jeremsdev.blogs.validator.BlogValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    private static final Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);
    private final BlogValidator blogValidator;
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    public BlogServiceImpl(BlogValidator blogValidator, BlogRepository blogRepository, BlogMapper blogMapper) {
        this.blogValidator = blogValidator;
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
    }

    @Override
    public BlogDTO create(BlogDTO blogDTO) {
        logger.info("Adding a new blog with title: {}", blogDTO.getTitle());

        blogValidator.validateBlogDTO(blogDTO);

        Blog blog = blogMapper.toEntity(blogDTO);
        blog = blogRepository.save(blog);

        logger.info("Blog added successfully with ID: {}", blog.getIdBlog());
        return blogMapper.toDTO(blog);
    }

    @Override
    public BlogDTO update(String idBlog, BlogDTO blogDTO) {
        logger.info("Updating blog with ID: {}", idBlog);
        blogValidator.validateBlogDTO(blogDTO);

        Blog blog = blogRepository.findById(idBlog)
                .orElseThrow(() -> {
                    logBlogNotFound(idBlog);
                    return new ResourceNotFoundException(resourceNotFoundMessage(idBlog));
                });

        blogMapper.updateEntityFromDTO(blogDTO, blog);

        logger.info("Blog updated successfully with ID: {}", idBlog);
        return blogMapper.toDTO(blogRepository.save(blog));
    }

    @Override
    public BlogDTO getById(String idBlog) {
        logger.info("Finding blog with ID: {}", idBlog);

        Blog blog = blogRepository.findById(idBlog)
                .orElseThrow(() -> {
                    logBlogNotFound(idBlog);
                    return new ResourceNotFoundException(resourceNotFoundMessage(idBlog));
                });

        logger.info("Blog with ID: {} found", idBlog);
        return blogMapper.toDTO(blog);
    }

    @Override
    public <T> List<BlogDTO> findByField(T field, T content) {
        logger.info("Finding blog with {}: {}", field,content);
        List<Blog> blogs = switch (field.toString()) {
            case "author" -> blogRepository.findByAuthor(content.toString());
            case "title" -> blogRepository.findByTitle(content.toString());
            default -> blogRepository.findByCategory((Category) content);
        };

        if (blogs.isEmpty()) {
            logger.warn("No blogs with this {} found in the database", field);
            throw new ResourceNotFoundException("No blogs found");
        }

        logger.info("Successfully retrieved {} blogs", blogs.size());
        return blogs.stream()
                .map(blogMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BlogDTO> findAll() {
        logger.info("Retrieving all blogs");

        List<Blog> blogs = blogRepository.findAll();

        if (blogs.isEmpty()) {
            logger.warn("No blogs found in the database");
            throw new ResourceNotFoundException("No blogs found");
        }

        logger.info("Successfully retrieved {} blogs", blogs.size());
        return blogs.stream()
                .map(blogMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String idBlog) {
        logger.info("Deleting blog with ID: {}", idBlog);

        if (!blogRepository.existsById(idBlog)) {
            logBlogNotFound(idBlog);
            throw new ResourceNotFoundException(resourceNotFoundMessage(idBlog));
        }

        blogRepository.deleteById(idBlog);
        logger.info("Blog with ID: {} deleted successfully", idBlog);
    }

    private void logBlogNotFound(String idBlog) {
        logger.error("Blog with ID: {} not found", idBlog);
    }

    private <T> String resourceNotFoundMessage(T field) {
        return "Blog with ID: " + field + " not found";
    }
}
