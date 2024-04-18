package com.CL1.GestorDeDocentes.models.mappers;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.responses.CategoryResponse;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    public List<CategoryResponse> toListResponse(List<Category> categories) {
        return categories.stream().map(this::toResponse).toList();
    }
}
