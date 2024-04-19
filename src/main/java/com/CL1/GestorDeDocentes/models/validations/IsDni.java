package com.CL1.GestorDeDocentes.models.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsDniValidator.class)
@Documented
public @interface IsDni {
    String message() default "El DNI es invalido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
