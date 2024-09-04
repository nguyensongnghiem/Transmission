package com.mobifone.transmission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
public class SiteOwnerDTO {
    @NotNull
    private int id;
    private String name;
}
