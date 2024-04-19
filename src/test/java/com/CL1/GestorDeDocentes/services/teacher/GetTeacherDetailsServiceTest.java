package com.CL1.GestorDeDocentes.services.teacher;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.Teacher;
import com.CL1.GestorDeDocentes.models.enums.Gender;
import com.CL1.GestorDeDocentes.models.mappers.TeacherMapper;
import com.CL1.GestorDeDocentes.models.responses.CategoryResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherDetailsResponse;
import com.CL1.GestorDeDocentes.repositories.TeacherRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetTeacherDetailsServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    private Teacher teacher;
    private TeacherDetailsResponse teacherResponse;

    @BeforeEach
    public void setUp() {
        teacher = Teacher.builder().id(1)
                .birthdate(LocalDate.of(2000, 4, 5))
                .dni("64292358")
                .name("Profesor 1")
                .category(new Category(1, "Category 1"))
                .email("profesor1@gmail.com")
                .gender(Gender.Hombre)
                .salary(BigDecimal.valueOf(2000))
                .build();

        teacherResponse = new TeacherDetailsResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getDni(),
                teacher.getSalary(),
                teacher.getGender(),
                teacher.getBirthdate(),
                new CategoryResponse(
                        teacher.getCategory().getId(),
                        teacher.getCategory().getName()
                )
        );
    }

    @Test
    @DisplayName("Should return one teacher by id")
    public void ReturnOneTeacherById() {
        Mockito.when(this.teacherRepository.findById(this.teacher.getId()))
                .thenReturn(Optional.ofNullable(this.teacher));

        Mockito.when(this.teacherMapper.toDetailsResponse(this.teacher))
                .thenReturn(teacherResponse);

        TeacherDetailsResponse response = this.teacherService.getOneDetails(this.teacher.getId());

        assertEquals(response.id(), this.teacher.getId());
    }

    @Test
    @DisplayName("Should throw a ResponseStatusException if the teacher does not exist")
    public void ThrowResponseStatusExceptionIfCategoryDoesNotExist() {
        Mockito.when(this.teacherRepository.findById(this.teacher.getId()))
                .thenThrow(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "El docente no existe"
                ));

        assertThrows(ResponseStatusException.class, () -> this.teacherService.getOneDetails(this.teacher.getId()));
    }

}
