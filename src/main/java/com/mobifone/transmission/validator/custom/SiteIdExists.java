package com.mobifone.transmission.validator.custom;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy= SiteIdExistsValidator.class)
public @interface SiteIdExists {
    String message() default "Cần nhập vào Site ID trạm đã có trong hệ thống, nếu chưa có cần tạo mới Site";
    Class[] groups() default {};
    Class[] payload() default {};
}
