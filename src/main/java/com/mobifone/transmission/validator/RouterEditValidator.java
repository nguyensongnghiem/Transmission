package com.mobifone.transmission.validator;

import com.mobifone.transmission.dto.RouterDTO;
import com.mobifone.transmission.repository.IRouterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class RouterEditValidator implements Validator {
@Autowired
IRouterRepository routerRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {
        RouterDTO routerDTO = (RouterDTO) target;
        String oldIp = routerRepository.findById(routerDTO.getId()).get().getIp();
        String oldName = routerRepository.findById(routerDTO.getId()).get().getName();

        if (routerRepository.findRouterByIp(routerDTO.getIp())!=null && !Objects.equals(oldIp, routerDTO.getIp())) {
            errors.rejectValue("ip",null,"Địa chỉ IP đã tồn tại trong hệ thống");
        }
        if (routerRepository.findRouterByName(routerDTO.getName())!=null && !Objects.equals(oldName, routerDTO.getName())) {
            errors.rejectValue("name",null,"Tên Router đã tồn tại trong hệ thống");
        }
    }
}
