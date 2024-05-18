package com.mobifone.transmission.validator.custom;

import com.mobifone.transmission.service.ISiteService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class SiteIdExistsValidator implements ConstraintValidator<SiteIdExists, String> {
    @Autowired
    private ISiteService siteService;

    @Override
    public void initialize(SiteIdExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String siteId, ConstraintValidatorContext constraintValidatorContext) {
        return siteService.findSitesBySiteId(siteId) == null;
    }
}
