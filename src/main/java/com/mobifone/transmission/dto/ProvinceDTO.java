package com.mobifone.transmission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Builder
public class ProvinceDTO {
    @NotNull
    private String id;
    private String name;
}
