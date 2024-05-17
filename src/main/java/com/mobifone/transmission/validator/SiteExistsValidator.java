package com.mobifone.transmission.validator;

import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.service.ISiteService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;

public class SiteExistsValidator implements ConstraintValidator<SiteExists, String> {
    @Autowired
    private ISiteService siteService;

    @Override
    public void initialize(SiteExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(String siteId, ConstraintValidatorContext constraintValidatorContext) {
        return siteService.findSitesBySiteId(siteId) == null;
    }
}
