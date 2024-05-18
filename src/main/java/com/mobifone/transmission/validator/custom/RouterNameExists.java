package com.mobifone.transmission.validator.custom;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy= RouterNameExistsValidator.class)
public @interface RouterNameExists {
    String message() default "Tên thiết bị đã tồn tại trong hệ thống";
    Class[] groups() default {};
    Class[] payload() default {};
}
