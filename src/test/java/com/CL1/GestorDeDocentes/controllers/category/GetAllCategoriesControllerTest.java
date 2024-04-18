package com.CL1.GestorDeDocentes.controllers.category;

import com.CL1.GestorDeDocentes.controllers.CategoryController;
import com.CL1.GestorDeDocentes.models.responses.CategoryResponse;
import com.CL1.GestorDeDocentes.services.category.CategoryService;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CategoryController.class)
@ExtendWith(MockitoExtension.class)
public class GetAllCategoriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private List<CategoryResponse> categoriesResponse;

    @BeforeEach
    public void setUp() {
        categoriesResponse = List.of(
                new CategoryResponse(1, "Category 1"),
                new CategoryResponse(2, "Category 2"),
                new CategoryResponse(3, "Category 3")
        );
    }

    @Test
    @DisplayName("Should return a status code 200 OK")
    public void ReturnStatus200Ok() throws Exception {
        BDDMockito.when(this.categoryService.getAll())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should verify if the total category responses is equal to total categories exists")
    public void VerifyTotalCategoryResponses() throws Exception {
        BDDMockito.when(this.categoryService.getAll())
                .thenReturn(this.categoriesResponse);

        ResultActions response = mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(jsonPath("$.size()", CoreMatchers.is(this.categoriesResponse.size())));
    }

    @Test
    @DisplayName("Should the response contain the first category")
    public void ResponseContainTheFirstCategory() throws Exception {
        BDDMockito.when(this.categoryService.getAll())
                .thenReturn(this.categoriesResponse);

        ResultActions response = mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON));

        response
                .andExpect(jsonPath("$[:1].id", CoreMatchers.is(List.of(this.categoriesResponse.get(0).id()))))
                .andExpect(jsonPath("$[:1].name", CoreMatchers.is(List.of(this.categoriesResponse.get(0).name()))));
    }
}
