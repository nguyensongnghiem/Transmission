package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.*;
import com.mobifone.transmission.validator.custom.SiteIdExists;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SiteDTO {
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

    private SiteOwner siteOwner;

    private SiteTransmissionType siteTransmissionType;

    private TransmissionOwner transmissionOwner;

    private String note;

    private Province province;
}
