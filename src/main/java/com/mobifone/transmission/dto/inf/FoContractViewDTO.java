package com.mobifone.transmission.dto.inf;

import java.time.LocalDate;
import java.util.List;

public interface FoContractViewDTO {
    int getId();
    String getContractNumber();
    String getContractName();
    LocalDate getSignedDate();
    LocalDate getEndDate();
    String getNote();
    TransmissionOwnerWithName getTransmissionOwner();
    interface TransmissionOwnerWithName {
        String getName();
    }
    List<HiredFoLineViewDTO> getHiredFoLineList();
}
