package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.TransmissionOwner;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class FoContractDTO {
    private Integer id;
    private String contractNumber;
    private String contractName;
    @PastOrPresent(message = "Ngày ký hợp đồng phải trước ngày hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate signedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private TransmissionOwner transmissionOwner;
    private String note;

}
