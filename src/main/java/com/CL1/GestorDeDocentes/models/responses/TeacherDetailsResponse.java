package com.CL1.GestorDeDocentes.models.responses;

import com.CL1.GestorDeDocentes.models.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TeacherDetailsResponse(
        int id,
        String name,
        String email,
        String dni,
        BigDecimal salary,
        Gender gender,
        LocalDate birthdate,
        CategoryResponse category
) {
}
