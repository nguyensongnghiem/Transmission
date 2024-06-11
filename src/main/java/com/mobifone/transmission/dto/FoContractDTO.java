package com.mobifone.transmission.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mobifone.transmission.model.HiredFoLine;
import com.mobifone.transmission.model.TransmissionOwner;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
public class FoContractDTO {
    @NotNull
    private String contractNumber;
    private String contractName;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @PastOrPresent(message = "Không sau ngày hiện tại")
    private LocalDate signedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private TransmissionOwner transmissionOwner;
    private String note;
}
