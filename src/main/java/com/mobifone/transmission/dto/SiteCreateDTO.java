package com.mobifone.transmission.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
@Builder
@Data
public class SiteCreateDTO {
    private Long id;
    @NotNull(message = "Thông tin bắt buộc")
    private String siteId;
    private String siteId2;
    private String siteName;
    @NotNull(message = "Thông tin bắt buộc")
    @Digits(integer = 2, fraction = 8,message = "Vĩ độ không phù hợp")
    private BigDecimal latitude;
    @NotNull(message = "Thông tin bắt buộc")
    @Digits(integer = 3, fraction = 8, message = "Kinh độ không phù hợp")
    private BigDecimal longitude;

    private String address;

    private SiteOwnerDTO siteOwner;

    private SiteTransmissionTypeDTO siteTransmissionType;

    private TransmissionOwnerDTO transmissionOwner;

    private String note;

    private ProvinceDTO province;
}
