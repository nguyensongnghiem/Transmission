package com.mobifone.transmission.validator.custom;

import com.mobifone.transmission.service.IRouterService;
import com.mobifone.transmission.service.ISiteService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RouterExistsValidator implements ConstraintValidator<RouterExists, String> {
    @Autowired
    private IRouterService routerService;

    @Override
    public void initialize(RouterExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String routerName, ConstraintValidatorContext constraintValidatorContext) {
        return routerService.findRouterByName(routerName) == null;
    }
}
