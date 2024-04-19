package com.CL1.GestorDeDocentes.services.teacher;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.Teacher;
import com.CL1.GestorDeDocentes.models.enums.Gender;
import com.CL1.GestorDeDocentes.models.requests.TeacherRequest;
import com.CL1.GestorDeDocentes.models.responses.MessageResponse;
import com.CL1.GestorDeDocentes.repositories.CategoryRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UpdateTeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    private Category dbCategory;
    private Teacher dbTeacher;

    @BeforeEach
    public void setUp() {
        dbCategory = new Category(1, "Category one");

        dbTeacher = Teacher.builder().id(1)
                .birthdate(LocalDate.of(2000, 4, 5))
                .dni("64292358")
                .name("Profesor 1")
                .category(dbCategory)
                .email("profesor1@gmail.com")
                .gender(Gender.Hombre)
                .salary(BigDecimal.valueOf(2000))
                .build();
    }

    @Test
    @DisplayName("Should return a message response if the teacher was updated successfully")
    public void ReturnMessageResponse() {
        Category otherCategory = new Category(3, "Update Category");

        TeacherRequest request = new TeacherRequest(
                "Profesor test",
                "76812917",
                LocalDate.of(2005, 4, 5),
                BigDecimal.valueOf(2000),
                "profesorTest@gmail.com",
                Gender.Hombre,
                otherCategory.getId()
        );

        Mockito.when(this.teacherRepository.findById(dbTeacher.getId()))
                .thenReturn(Optional.ofNullable(dbTeacher));

        Mockito.when(this.categoryRepository.findById(otherCategory.getId()))
                .thenReturn(Optional.of(otherCategory));

        Mockito.when(this.teacherRepository.save(Mockito.any(Teacher.class)))
                .thenReturn(dbTeacher);

        MessageResponse response = this.teacherService.update(dbTeacher.getId(), request);

        assertEquals(response.message(), "Se han guardado los cambios del docente correctamente");
    }

    @Test
    @DisplayName("Should throw a ResponseStatusException if the category does not exist")
    public void ThrowResponseStatusExceptionIfTheCategoryDoesNotExist() {
        TeacherRequest request = new TeacherRequest(
                "Profesor test",
                "76812917",
                LocalDate.of(2005, 4, 5),
                BigDecimal.valueOf(2000),
                "profesorTest@gmail.com",
                Gender.Hombre,
                1000
        );

        Mockito.when(this.teacherRepository.findById(dbTeacher.getId()))
                .thenReturn(Optional.ofNullable(dbTeacher));

        Mockito.when(this.categoryRepository.findById(request.categoryId()))
                .thenThrow(new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "La categoria no existe"
                ));

        assertThrows(ResponseStatusException.class, () -> this.teacherService.update(
                this.dbTeacher.getId(), request
        ));
    }
}
