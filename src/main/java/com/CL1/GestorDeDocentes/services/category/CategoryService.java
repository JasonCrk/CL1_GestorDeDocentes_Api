package com.CL1.GestorDeDocentes.services.category;

import com.CL1.GestorDeDocentes.models.responses.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAll();
}
