package com.mobifone.transmission.validator.custom;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=SiteExistsValidator.class)
public @interface  SiteExists {
    String message() default "Site ID đã tồn tại trong hệ thống";
    Class[] groups() default {};
    Class[] payload() default {};
}
