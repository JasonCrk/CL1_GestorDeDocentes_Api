package com.CL1.GestorDeDocentes.models.mappers;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.Teacher;
import com.CL1.GestorDeDocentes.models.enums.Gender;
import com.CL1.GestorDeDocentes.models.responses.CategoryResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherDetailsResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TeacherMapperTest {

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private TeacherMapper teacherMapper;

    private List<Teacher> teachers;
    private Category category;

    @BeforeEach
    public void setUp() {
        this.category = new Category(1, "Category test");

        this.teachers = List.of(
                Teacher.builder().id(1)
                        .birthdate(LocalDate.of(2000, 4, 5))
                        .dni("64292358")
                        .name("Profesor 1")
                        .category(new Category(1, "Category 1"))
                        .email("profesor1@gmail.com")
                        .gender(Gender.Hombre)
                        .salary(BigDecimal.valueOf(2000))
                        .build(),
                Teacher.builder().id(2)
                        .birthdate(LocalDate.of(2000, 4, 5))
                        .dni("71392859")
                        .name("Profesor 2")
                        .category(new Category(2, "Category 2"))
                        .email("profesor2@gmail.com")
                        .gender(Gender.Hombre)
                        .salary(BigDecimal.valueOf(3500))
                        .build(),
                Teacher.builder().id(3)
                        .birthdate(LocalDate.of(2000, 4, 5))
                        .dni("75662831")
                        .name("Profesor 3")
                        .category(new Category(3, "Category 3"))
                        .email("profesor3@gmail.com")
                        .gender(Gender.Mujer)
                        .salary(BigDecimal.valueOf(3000))
                        .build()
        );
    }

    @Test
    @DisplayName("toResponse() => Should return a teacher response from a teacher entity")
    public void ToResponse_ReturnATeacherResponseFromATeacherEntity() {
        var teacher = teachers.get(0);

        TeacherResponse response = teacherMapper.toResponse(teacher);

        assertEquals(response.id(), teacher.getId());
        assertEquals(response.name(), teacher.getName());
        assertEquals(response.email(), teacher.getEmail());
        assertEquals(response.gender(), teacher.getGender());
        assertEquals(response.category(), teacher.getCategory().getName());
    }

    @Test
    @DisplayName("toListResponse() => Should return a list of teacher responses from a list of teacher entities")
    public void ToListResponse_ReturnTeacherResponsesFromATeacherEntities() {
        List<TeacherResponse> response = this.teacherMapper.toListResponse(teachers);

        assertEquals(this.teachers.size(), response.size());
    }

    @Test
    @DisplayName("toDetailsResponse() => Should return the teacher details from a teacher entity")
    public void ToDetailsResponse_ReturnATeacherDetailsFromATeacherEntity() {
        var teacher = teachers.get(0);

        Mockito.when(categoryMapper.toResponse(teacher.getCategory()))
                .thenReturn(new CategoryResponse(category.getId(), category.getName()));

        TeacherDetailsResponse response = teacherMapper.toDetailsResponse(teacher);

        assertEquals(response.id(), teacher.getId());
        assertEquals(response.name(), teacher.getName());
        assertEquals(response.email(), teacher.getEmail());
        assertEquals(response.dni(), teacher.getDni());
        assertEquals(response.salary(), teacher.getSalary());
        assertEquals(response.gender(), teacher.getGender());
        assertEquals(response.birthdate(), teacher.getBirthdate());
        assertEquals(response.category(), this.categoryMapper.toResponse(teacher.getCategory()));
    }
}
