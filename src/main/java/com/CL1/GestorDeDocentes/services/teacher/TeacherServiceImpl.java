package com.CL1.GestorDeDocentes.services.teacher;

import com.CL1.GestorDeDocentes.models.Category;
import com.CL1.GestorDeDocentes.models.Teacher;
import com.CL1.GestorDeDocentes.models.mappers.TeacherMapper;
import com.CL1.GestorDeDocentes.models.requests.TeacherRequest;
import com.CL1.GestorDeDocentes.models.responses.MessageResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherDetailsResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherResponse;
import com.CL1.GestorDeDocentes.repositories.CategoryRepository;
import com.CL1.GestorDeDocentes.repositories.TeacherRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CategoryRepository categoryRepository;

    private final TeacherMapper teacherMapper;

    @Override
    @Transactional(readOnly = true)
    public List<TeacherResponse> getAll() {
        return teacherMapper.toListResponse(this.teacherRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherDetailsResponse getOneDetails(int teacherId) {
        Teacher teacher = this.teacherRepository
                .findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El docente no existe"));

        return this.teacherMapper.toDetailsResponse(teacher);
    }

    @Override
    @Transactional
    public MessageResponse create(TeacherRequest request) {
        Category category = this.categoryRepository
                .findById(request.categoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "La categoria no existe"
                ));

        Teacher newTeacher = Teacher.builder()
                .dni(request.dni())
                .email(request.email())
                .name(request.name())
                .gender(request.gender())
                .salary(request.salary())
                .category(category)
                .birthdate(request.birthdate())
                .build();

        this.teacherRepository.save(newTeacher);

        return new MessageResponse("Se ha registrado el docente correctamente");
    }

    @Override
    @Transactional
    public MessageResponse update(int teacherId, TeacherRequest request) {
        Teacher teacher = this.teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El docente no existe"
                ));

        if (teacher.getCategory().getId() != request.categoryId()) {
            var category = this.categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "La categoria no existe"
                    ));

            teacher.setCategory(category);
        }

        teacher.setDni(request.dni());
        teacher.setBirthdate(request.birthdate());
        teacher.setGender(request.gender());
        teacher.setEmail(request.email());
        teacher.setSalary(request.salary());

        this.teacherRepository.save(teacher);

        return new MessageResponse("Se han guardado los cambios del docente correctamente");
    }

    @Override
    @Transactional
    public MessageResponse delete(int teacherId) {
        Teacher teacher = this.teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "El docente no existe"
                ));

        this.teacherRepository.delete(teacher);

        return new MessageResponse("El docente fue eliminado exitosamente");
    }
}
