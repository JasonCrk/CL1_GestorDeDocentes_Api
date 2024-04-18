package com.CL1.GestorDeDocentes.models.mappers;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.responses.CategoryResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryMapperTest {

    private CategoryMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new CategoryMapper();
    }

    @Test
    @DisplayName("toResponse() => Should return category response from a category entity")
    public void ToResponse_ReturnCategoryResponseFromACategoryEntity() {
        Category category = new Category(1, "Test category");

        CategoryResponse categoryResponse = this.mapper.toResponse(category);

        assertEquals(categoryResponse.id(), category.getId());
        assertEquals(categoryResponse.name(), category.getName());
    }

    @Test
    @DisplayName("toListResponse() => Should return a list of category responses from a list of category entities")
    public void ToListResponse_ReturnCategoryResponsesFromACategoryEntities() {
        List<Category> categories = List.of(
                new Category(1, "Category 1"),
                new Category(2, "Category 2"),
                new Category(3, "Category 3")
        );

        List<CategoryResponse> categoryResponses = this.mapper.toListResponse(categories);

        for (int index = 0; index < categoryResponses.size(); index++) {
            var categoryResponse = categoryResponses.get(index);
            var category = categories.get(index);

            assertEquals(categoryResponse.id(), category.getId());
            assertEquals(categoryResponse.name(), category.getName());
        }
    }
}
