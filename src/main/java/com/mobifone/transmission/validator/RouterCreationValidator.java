package com.mobifone.transmission.validator;

import com.mobifone.transmission.dto.RouterDTO;
import com.mobifone.transmission.dto.SiteDTO;
import com.mobifone.transmission.repository.IRouterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RouterCreationValidator implements Validator {
@Autowired
IRouterRepository routerRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {
        RouterDTO routerDTO = (RouterDTO) target;
        if (routerRepository.findRouterByIp(routerDTO.getIp())!=null) {
            errors.rejectValue("ip",null,"Địa chỉ IP đã tồn tại trong hệ thống");
        }
        if (routerRepository.findRouterByName(routerDTO.getName())!=null) {
            errors.rejectValue("name",null,"Tên Router đã tồn tại trong hệ thống");
        }
    }
}
