package com.mobifone.transmission.validator.custom;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy= RouterIpExistsValidator.class)
public @interface RouterIpExists {
    String message() default "Địa chỉ IP đã tồn tại trong hệ thống";
    Class[] groups() default {};
    Class[] payload() default {};
}
