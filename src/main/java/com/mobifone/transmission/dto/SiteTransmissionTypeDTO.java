package com.mobifone.transmission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
public class SiteTransmissionTypeDTO {
    @NotNull
    private int id;
    private String name;
}
