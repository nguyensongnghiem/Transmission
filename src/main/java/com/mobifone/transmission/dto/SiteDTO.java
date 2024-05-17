package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.*;
import com.mobifone.transmission.validator.SiteExists;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Getter
@Setter
public class SiteDTO implements Validator {
    @NotNull(message = "Thông tin bắt buộc")
    @SiteExists
    private String siteId;

    private String siteId2;

    private String siteName;
    @NotNull(message = "Thông tin bắt buộc")
    @Digits(integer = 2, fraction = 8,message = "Vĩ độ không phù hợp")
    private Float latitude;
    @NotNull(message = "Thông tin bắt buộc")
    @Digits(integer = 3, fraction = 8, message = "Kinh độ không phù hợp")
    private Float longitude;

    private String address;

    private SiteOwner siteOwner;

    private SiteTransmissionType siteTransmissionType;

    private TransmissionOwner transmissionOwner;

    private String note;

    private Province province;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SiteDTO siteDTO = (SiteDTO) target;



    }
}
