package com.jeremsdev.blogs.validator;

import com.jeremsdev.blogs.dto.BlogDTO;
import com.jeremsdev.blogs.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class BlogValidator {

    /**
     * Valid blog DTO.
     *
     * @param blogDTO blog DTO.
     */
    public void validateBlogDTO(BlogDTO blogDTO) {
        if (blogDTO == null) {
            throw new ValidationException("Blog data cannot be null");
        }

        if (blogDTO.getCategory() == null) {
            throw new ValidationException("Category cannot be null");
        }

        validateRequiredField(blogDTO.getAuthor(),"Author");
        validateRequiredField(blogDTO.getTitle(),"Title");
        validateRequiredField(blogDTO.getContent(),"Content");
        validateRequiredField(blogDTO.getPublicationDate(),"Publication Date");
    }

    /**
     * Checks whether a field is not null and not empty.
     *
     * @param field the field to be checked.
     */
    private <T> void validateRequiredField(T field, String attribute) {
        if (field == null || (((String) field).isBlank())) {
            throw new ValidationException(attribute + " cannot be null or empty");
        }
    }
}
