package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.model.TransmissionOwner;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
public class HiredFoLineDTO {

    private FoContract foContract;
    private Integer coreQuantity;

    private Site nearSite;
    private Site farSite;
    private Integer cost;
    private float designedDistance;
    private float finalDistance;
    private String note;
}
