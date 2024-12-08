package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaselineDTO {
    private int id;
    @NotNull(message = "Thông tin bắt buộc")
    @Min(value = 0, message = "Băng thông phải lớn hơn hoặc bằng 0")
    private float speed;
    @NotNull(message = "Thông tin bắt buộc")
    @Min(value = 0, message = "Đơn giá phải lớn hơn hoặc bằng 0")
    private float cost;
    @NotNull(message = "Thông tin bắt buộc")
    @Min(value = 1, message = "Số lượng phải lớn hơn hoặc bằng 1")
    private int quantity;
    private LeaseLineConnectType leaseLineConnectType;
    private TransmissionOwner transmissionOwner;
    @NotNull(message = "Thông tin bắt buộc")
    private Site site;
    private boolean active;
    private String note;
}
