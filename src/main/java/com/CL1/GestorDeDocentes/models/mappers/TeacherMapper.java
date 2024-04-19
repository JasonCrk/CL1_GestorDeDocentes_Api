package com.CL1.GestorDeDocentes.models.mappers;

import com.CL1.GestorDeDocentes.models.Teacher;
import com.CL1.GestorDeDocentes.models.responses.TeacherDetailsResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    public TeacherResponse toResponse(Teacher teacher) {
        return new TeacherResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getGender(),
                teacher.getCategory().getName()
        );
    }

    public List<TeacherResponse> toListResponse(List<Teacher> teachers) {
        return teachers.stream().map(this::toResponse).toList();
    }

    public TeacherDetailsResponse toDetailsResponse(Teacher teacher) {
        return new TeacherDetailsResponse(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getDni(),
                teacher.getSalary(),
                teacher.getGender(),
                teacher.getBirthdate(),
                this.categoryMapper.toResponse(teacher.getCategory())
        );
    }
}
