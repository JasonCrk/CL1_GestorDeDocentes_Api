package com.CL1.GestorDeDocentes.services.category;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.mappers.CategoryMapper;
import com.CL1.GestorDeDocentes.models.responses.CategoryResponse;
import com.CL1.GestorDeDocentes.repositories.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GetAllCategoriesServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private List<Category> dbCategories;

    @BeforeEach
    public void setUp() {
         dbCategories = List.of(
                new Category(1, "Categoria 1"),
                new Category(2, "Categoria 2"),
                new Category(3, "Categoria 3"),
                new Category(4, "Categoria 4"),
                new Category(5, "Categoria 5")
        );
    }

    @Test
    @DisplayName("Should return all categories")
    public void ReturnAllCategories() {
        Mockito.when(this.categoryRepository.findAll())
                .thenReturn(dbCategories);

        List<CategoryResponse> serviceCategories = this.categoryService.getAll();

        for (int index = 0; index < serviceCategories.size(); index++) {
            assertEquals(serviceCategories.get(index).id(), dbCategories.get(index).getId());
        }
    }
}
