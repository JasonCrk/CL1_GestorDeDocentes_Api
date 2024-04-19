package com.CL1.GestorDeDocentes.services.teacher;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.Teacher;
import com.CL1.GestorDeDocentes.models.enums.Gender;
import com.CL1.GestorDeDocentes.models.responses.MessageResponse;
import com.CL1.GestorDeDocentes.repositories.CategoryRepository;
import com.CL1.GestorDeDocentes.repositories.TeacherRepository;

import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DeleteTeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    private Teacher teacher;

    @BeforeEach
    public void setUp() {
        teacher = Teacher.builder().id(1)
                .birthdate(LocalDate.of(2000, 4, 5))
                .dni("75662831")
                .name("Profesor 1")
                .category(new Category(1, "Category 1"))
                .email("profesor3@gmail.com")
                .gender(Gender.Mujer)
                .salary(BigDecimal.valueOf(3000))
                .build();
    }

    @Test
    @DisplayName("Should return a message response")
    public void ReturnMessageResponse() {
        Mockito.when(this.teacherRepository.findById(teacher.getId()))
                .thenReturn(Optional.ofNullable(teacher));

        Mockito.doNothing().when(this.teacherRepository).delete(teacher);

        MessageResponse response = this.teacherService.delete(teacher.getId());

        Mockito.verify(this.teacherRepository, Mockito.times(1)).delete(teacher);

        Assertions.assertEquals(response.message(), "El docente fue eliminado exitosamente");
    }

    @Test
    @DisplayName("Should throw a ResponseStatusException if the teacher does not exist")
    public void ThrowResponseStatusExceptionIfTheTeacherDoesNotExist() {
        int notExistTeacherId = 100;

        Mockito.when(this.teacherRepository.findById(notExistTeacherId))
                .thenThrow(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "El docente no existe"
                ));

        assertThrows(ResponseStatusException.class, () -> this.teacherService.delete(notExistTeacherId));
    }
}
