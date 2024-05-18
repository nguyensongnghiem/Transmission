package com.mobifone.transmission.validator.custom;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy= SiteIdExistsValidator.class)
public @interface SiteIdExists {
    String message() default "Site ID đã tồn tại trong hệ thống";
    Class[] groups() default {};
    Class[] payload() default {};
}
