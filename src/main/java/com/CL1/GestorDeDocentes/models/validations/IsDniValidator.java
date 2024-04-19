package com.CL1.GestorDeDocentes.models.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsDniValidator implements ConstraintValidator<IsDni, String> {

    @Override
    public boolean isValid(String dni, ConstraintValidatorContext constraintValidatorContext) {
        return dni.matches("");
    }
}
