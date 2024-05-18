package com.mobifone.transmission.validator.custom;

import com.mobifone.transmission.service.IRouterService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RouterIpExistsValidator implements ConstraintValidator<RouterIpExists, String> {
    @Autowired
    private IRouterService routerService;

    @Override
    public void initialize(RouterIpExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String routerIp, ConstraintValidatorContext constraintValidatorContext) {
        return routerService.findRouterByIp(routerIp) == null;
    }
}
