package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.TransmissionOwner;
import jakarta.validation.constraints.NotBlank;
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
public class FoContractCreateDTO {
    @NotBlank(message = "Yêu cầu nhập số hợp đồng")
    private String contractNumber;
    @NotBlank(message = "Yêu cầu nhập tên hợp đồng")
    private String contractName;
    @PastOrPresent(message = "Ngày ký hợp đồng phải trước ngày hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Yêu cầu nhập ngày ký hợp đồng")
    private LocalDate signedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Yêu cầu nhập ngày kết thúc hợp đồng")
    private LocalDate endDate;    
    private String contractUrl;    
    private TransmissionOwner transmissionOwner;
    private String note;    
}
