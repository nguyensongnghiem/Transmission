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
        String getName();
    }
    SiteTransmissionTypeWithNameDTO getSiteTransmissionType();
    interface SiteTransmissionTypeWithNameDTO {
        String getName();
    }
    TransmissionOwnerWithNameDTO getTransmissionOwner();
    interface TransmissionOwnerWithNameDTO {
        String getName();
    }
    interface RouterTypeWithNameDTO {
        String getName();
    }
    String getNote();
    ProvinceWithNameDTO getProvince();
    interface ProvinceWithNameDTO {
        String getName();
    }
}
