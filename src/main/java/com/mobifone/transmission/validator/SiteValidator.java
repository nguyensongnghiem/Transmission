package com.mobifone.transmission.validator;

import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.repository.ISiteRepository;
import com.mobifone.transmission.service.ISiteService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.List;
@Component
public class SiteValidator implements Validator {
@Autowired
ISiteRepository siteRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {
    }
}
