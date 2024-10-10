package com.mobifone.transmission.dto.inf;

import java.math.BigDecimal;

public interface SimpleSiteDTO {
    Long getId();
    String getSiteId();
    BigDecimal getLatitude();
    BigDecimal getLongitude();
    SiteViewDTO.SiteTransmissionTypeWithNameDTO getSiteTransmissionType();

    SiteViewDTO.TransmissionOwnerWithNameDTO getTransmissionOwner();


    boolean getActive();
}
