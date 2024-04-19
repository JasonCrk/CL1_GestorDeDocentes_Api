package com.CL1.GestorDeDocentes.services.teacher;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.Teacher;
import com.CL1.GestorDeDocentes.models.enums.Gender;
import com.CL1.GestorDeDocentes.models.mappers.TeacherMapper;
import com.CL1.GestorDeDocentes.models.responses.TeacherResponse;
import com.CL1.GestorDeDocentes.repositories.TeacherRepository;

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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetAllTeachersServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    private List<Teacher> dbTeachers;
    private final List<TeacherResponse> teacherResponses = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        dbTeachers = List.of(
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

        dbTeachers.forEach(teacher -> {
            teacherResponses.add(new TeacherResponse(
                    teacher.getId(),
                    teacher.getName(),
                    teacher.getEmail(),
                    teacher.getGender(),
                    teacher.getCategory().getName()
            ));
        });
    }

    @Test
    @DisplayName("Should return all teachers")
    public void ReturnAllTeachers() {
        Mockito.when(this.teacherRepository.findAll()).thenReturn(dbTeachers);
        Mockito.when(this.teacherMapper.toListResponse(dbTeachers)).thenReturn(teacherResponses);

        List<TeacherResponse> response = this.teacherService.getAll();

        assertEquals(this.dbTeachers.size(), response.size());
    }
}
