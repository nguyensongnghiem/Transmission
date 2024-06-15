package com.mobifone.transmission.validator;

import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SiteCreationValidator implements Validator {
@Autowired
ISiteService siteService;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {
        SiteDTO siteDTO = (SiteDTO) target;
        if (siteService.findSitesBySiteId(siteDTO.getSiteId())!=null) {
            errors.rejectValue("siteId",null,"SiteID đã tồn tại trong hệ thống");
        }
    }
}
