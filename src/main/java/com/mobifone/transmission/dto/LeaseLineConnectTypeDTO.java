package com.mobifone.transmission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
public class LeaseLineConnectTypeDTO {
    @NotNull
    private int id;
    private String name;
}
