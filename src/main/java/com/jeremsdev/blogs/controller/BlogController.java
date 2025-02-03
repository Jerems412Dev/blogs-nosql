package com.jeremsdev.blogs.controller;

import com.jeremsdev.blogs.dto.BlogDTO;
import com.jeremsdev.blogs.exception.ApiRequestException;
import com.jeremsdev.blogs.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    private final BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<BlogDTO> create(@RequestBody BlogDTO blogDTO) {
        logger.info("Received request to add a new Blog: {}", blogDTO);
        try {
            BlogDTO addedBlog = blogService.create(blogDTO);
            logger.info("Blog added successfully: {}", addedBlog);
            return new ResponseEntity<>(addedBlog, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding Blog: {}", e.getMessage());
            throw new ApiRequestException("Error adding Blog: " + new Exception().getMessage());
        }
    }

    @PutMapping("/update/{idBlog}")
    public ResponseEntity<BlogDTO> update(@PathVariable String idBlog,
                                          @RequestBody BlogDTO blogDTO) {
        logger.info("Received request to update Blog with ID: {}", idBlog);
        try {
            BlogDTO updatedBlog = blogService.update(idBlog, blogDTO);
            logger.info("Blog with ID {} updated successfully", idBlog);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating Blog with ID {}: {}", idBlog, e.getMessage());
            throw new ApiRequestException("Error updating Blog with ID " + idBlog + ": " + new Exception().getMessage());
        }
    }

    @GetMapping("/getbyid/{idBlog}")
    public ResponseEntity<BlogDTO> getById(@PathVariable String idBlog) {
        logger.info("Received request to retrieve Blog with ID: {}", idBlog);
        try {
            BlogDTO blogDTO = blogService.getById(idBlog);
            if (blogDTO == null) {
                throw new ApiRequestException("Blog with ID " + idBlog + " not found");
            }
            return new ResponseEntity<>(blogDTO, HttpStatus.OK);
        } catch (ApiRequestException ex) {
            logger.error("Error retrieving Blog with ID {}: {}", idBlog, ex.getMessage());
            throw new ApiRequestException();
        } catch (Exception e) {
            logger.error("Error retrieving Blog with ID {}: {}", idBlog, e.getMessage());
            throw new ApiRequestException("Error retrieving Blog with ID " + idBlog + ": " + new Exception().getMessage());
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<List<BlogDTO>> findAll() {
        logger.info("Received request to retrieve all Blogs");
        try {
            List<BlogDTO> blogs = blogService.findAll();
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error retrieving Blog list: {}", e.getMessage());
            throw new ApiRequestException("Error retrieving Blog list: " + new Exception().getMessage());
        }
    }

    @DeleteMapping("/delete/{idBlog}")
    public ResponseEntity<Void> delete(@PathVariable String idBlog) {
        logger.info("Received request to delete Blog with ID: {}", idBlog);
        try {
            blogService.delete(idBlog);
            logger.info("Blog with ID {} deleted successfully", idBlog);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting Blog with ID {}: {}", idBlog, e.getMessage());
            throw new ApiRequestException("Error deleting Blog with ID " + idBlog + ": " + new Exception().getMessage());
        }
    }

    @GetMapping("/findbyfield/{field}/{content}")
    public ResponseEntity<List<BlogDTO>> findByField(
            @PathVariable String field,
            @PathVariable String content) {
        logger.info("Received request to find blogs by {} with value: {}", field, content);
        try {
            List<BlogDTO> blogs = blogService.findByField(field, content);
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (ApiRequestException ex) {
            logger.error("No blogs found for field {} with value {}: {}", field, content, ex.getMessage());
            throw new ApiRequestException("No blogs found for the given field and content");
        } catch (Exception e) {
            logger.error("Error occurred while retrieving blogs for field {} with value {}: {}", field, content, e.getMessage());
            throw new ApiRequestException("An error occurred while retrieving blogs: " + new Exception().getMessage());
        }
    }

    @GetMapping("/findbyfieldindex/{field}/{content}")
    public ResponseEntity<List<BlogDTO>> findByFieldIndex(
            @PathVariable String field,
            @PathVariable String content) {
        logger.info("Received request to find blogs in index by {} with value: {}", field, content);
        try {
            List<BlogDTO> blogs = blogService.findByFieldIndex(field, content);
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (ApiRequestException ex) {
            logger.error("No blogs found in index for field {} with value {}: {}", field, content, ex.getMessage());
            throw new ApiRequestException("No blogs found in the index for the given field and content");
        } catch (Exception e) {
            logger.error("Error occurred while retrieving blogs from index for field {} with value {}: {}", field, content, e.getMessage());
            throw new ApiRequestException("An error occurred while retrieving blogs from index: " + new Exception().getMessage());
        }
    }

    @GetMapping("/findbycontain")
    public ResponseEntity<List<BlogDTO>> findByContain(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String content) {
        logger.info("Received request to find blogs by title, description, or content");
        try {
            List<BlogDTO> blogs = blogService.findByContain(titre, description, content);
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } catch (ApiRequestException ex) {
            logger.error("No blogs found with the given criteria: title={}, description={}, content={}: {}", titre, description, content, ex.getMessage());
            throw new ApiRequestException("No blogs found for the given criteria");
        } catch (Exception e) {
            logger.error("Error occurred while retrieving blogs: title={}, description={}, content={}: {}", titre, description, content, e.getMessage());
            throw new ApiRequestException("An error occurred while retrieving blogs: " + new Exception().getMessage());
        }
    }

}
