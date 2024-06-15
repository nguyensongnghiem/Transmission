package com.mobifone.transmission.validator;

import com.mobifone.transmission.dto.FoContractDTO;
import com.mobifone.transmission.repository.IFoContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FoContractCreationValidator implements Validator {
    @Autowired
    IFoContractRepository foContractRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {
        FoContractDTO foContractDTO = (FoContractDTO) target;
        if (foContractDTO.getEndDate().isBefore(foContractDTO.getSignedDate())) {
            errors.rejectValue("endDate", null,"Ngày hết hạn hơp đồng phải sau ngày ký hợp đồng");
        }

    }
}
