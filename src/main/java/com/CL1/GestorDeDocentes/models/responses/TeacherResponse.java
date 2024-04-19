package com.CL1.GestorDeDocentes.models.responses;

import com.CL1.GestorDeDocentes.models.enums.Gender;

public record TeacherResponse(
        int id,
        String name,
        String email,
        Gender gender,
        String category
) {
}
