package com.CL1.GestorDeDocentes.repositories;

import com.CL1.GestorDeDocentes.models.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
