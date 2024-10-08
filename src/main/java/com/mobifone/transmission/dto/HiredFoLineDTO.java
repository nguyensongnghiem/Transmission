package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.Site;
import com.mobifone.transmission.model.TransmissionOwner;
import com.mobifone.transmission.validator.custom.SiteIdExists;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
public class HiredFoLineDTO {
    private Integer id;
    private FoContract foContract;
    @Positive(message = "Yêu cầu lớn hơn 0")
    private Integer coreQuantity;
    @NotNull(message = "Không được để trống")
    private Site nearSite;
    @NotNull(message = "Không được để trống")
    private Site farSite;
    @PositiveOrZero(message = "Yêu cầu lớn hơn hoặc bằng 0")
    private Integer cost;
    @PositiveOrZero(message = "Yêu cầu lớn hơn hoặc bằng 0")
    private float designedDistance;
    @PositiveOrZero(message = "Yêu cầu lớn hơn hoặc bằng 0")
    private float finalDistance;
    private boolean active;
    private String note;
}
