package com.CL1.GestorDeDocentes.repositories;

import com.CL1.GestorDeDocentes.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
