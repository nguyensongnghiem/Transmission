package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.*;
import com.mobifone.transmission.validator.custom.RouterIpExists;
import com.mobifone.transmission.validator.custom.RouterNameExists;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouterDTO {
    private Long id;
    @NotNull(message = "Thông tin bắt buộc")
    private String name;
    private RouterType routerType;
    private TransmissionDeviceType transmissionDeviceType;

    @NotNull(message = "Thông tin bắt buộc")
    private Site site;
    @NotNull(message = "Thông tin bắt buộc")
    @Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$",message = "Format IP không đúng")
    private String ip;
    private String note;
}
