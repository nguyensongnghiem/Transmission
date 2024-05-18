package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.*;
import com.mobifone.transmission.validator.custom.SiteIdExists;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SiteDTO {
    @NotNull(message = "Thông tin bắt buộc")
    @SiteIdExists
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
}
