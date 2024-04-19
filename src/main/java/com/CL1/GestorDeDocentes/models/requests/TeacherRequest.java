package com.CL1.GestorDeDocentes.models.requests;

import com.CL1.GestorDeDocentes.models.enums.Gender;
import com.CL1.GestorDeDocentes.models.validations.IsDni;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TeacherRequest(
        @NotBlank(message = "El nombre es requerido")
        String name,

        @IsDni
        @NotBlank(message = "El DNI es requerido")
        String dni,

        @NotNull(message = "La fecha de nacimiento es requerido")
        LocalDate birthdate,

        @DecimalMin(value = "0.0", inclusive = false)
        @NotNull(message = "El salario es requerido")
        BigDecimal salary,

        @Email(message = "El email es invalido")
        String email,

        @NotNull(message = "El genero es requerido")
        Gender gender,

        @NotNull(message = "El ID de la categoria es requerido")
        @Min(value = 1)
        int categoryId
) {
}
