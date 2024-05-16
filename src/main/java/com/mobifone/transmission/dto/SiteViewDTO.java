package com.mobifone.transmission.dto;

import com.mobifone.transmission.model.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
//@Builder
public class SiteViewDTO {
    private Long id;
    private String siteId;
    private String siteId2;
    private String siteName;
    private Float latitude;
    private Float longitude;
    private String address;
    private String siteOwner;
    private String siteTransmissionType;
    private String transmissionOwner;
    private String note;
    private String province;
    public SiteViewDTO(Site site) {
        this.id = site.getId();
        this.siteId = site.getSiteId();
        this.siteId2 = site.getSiteId2();
        this.latitude = site.getLatitude();
        this.longitude = site.getLongitude();
        this.address = site.getAddress();
        this.siteOwner = site.getSiteOwner() == null ? null : site.getSiteOwner().getName();
        this.siteTransmissionType = site.getSiteTransmissionType() == null ? null : site.getSiteTransmissionType().getName();
        this.transmissionOwner = site.getTransmissionOwner() == null ? null : site.getTransmissionOwner().getName();
        this.note = site.getNote();
        this.province = site.getProvince().getName();
    }
}
