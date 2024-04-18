package com.CL1.GestorDeDocentes.services.category;

import com.CL1.GestorDeDocentes.models.mappers.CategoryMapper;
import com.CL1.GestorDeDocentes.models.responses.CategoryResponse;
import com.CL1.GestorDeDocentes.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAll() {
        return categoryMapper.toListResponse(this.categoryRepository.findAll());
    }
}
