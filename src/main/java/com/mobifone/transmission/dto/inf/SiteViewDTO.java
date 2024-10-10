package com.mobifone.transmission.dto.inf;

import com.mobifone.transmission.model.SiteOwner;
import com.mobifone.transmission.model.TransmissionOwner;

import java.math.BigDecimal;

public interface SiteViewDTO {
    Long getId();
    String getSiteId();
    String getSiteId2();
    String getSiteName();
    BigDecimal getLatitude();
    BigDecimal getLongitude();
    String getAddress();
    SiteOwnerWithNameDTO getSiteOwner();
    interface SiteOwnerWithNameDTO {
        int getId();
        String getName();
    }
    SiteTransmissionTypeWithNameDTO getSiteTransmissionType();
    interface SiteTransmissionTypeWithNameDTO {
        int getId();
        String getName();
    }
    TransmissionOwnerWithNameDTO getTransmissionOwner();
    interface TransmissionOwnerWithNameDTO {
        int getId();
        String getName();
    }

    String getNote();
    ProvinceWithNameDTO getProvince();
    interface ProvinceWithNameDTO {
        String getId();
        String getName();
    }
    boolean getActive();
}
