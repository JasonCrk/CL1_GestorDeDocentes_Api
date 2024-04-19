package com.CL1.GestorDeDocentes.models.requests;

import com.CL1.GestorDeDocentes.models.enums.Gender;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TeacherRequest(
        @NotBlank(message = "El nombre es requerido")
        String name,
        String dni,
        LocalDate birthdate,
        BigDecimal salary,
        String email,
        Gender gender,
        int categoryId
) {
}
