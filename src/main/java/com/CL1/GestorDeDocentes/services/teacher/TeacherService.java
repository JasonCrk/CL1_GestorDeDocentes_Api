package com.CL1.GestorDeDocentes.services.teacher;

import com.CL1.GestorDeDocentes.models.requests.TeacherRequest;
import com.CL1.GestorDeDocentes.models.responses.MessageResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherDetailsResponse;
import com.CL1.GestorDeDocentes.models.responses.TeacherResponse;

import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAll();
    TeacherDetailsResponse getOneDetails(int teacherId);
    MessageResponse create(TeacherRequest request);
    MessageResponse update(int teacherId, TeacherRequest request);
    MessageResponse delete(int teacherId);
}
