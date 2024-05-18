package com.mobifone.transmission.dto.inf;
public interface RouterViewDTO {
    Long getId();
    String getName();
    RouterTypeWithNameDTO getRouterType();
    interface RouterTypeWithNameDTO {
        String getName();
    }
    TransmissionDeviceTypeWithNameDTO getTransmissionDeviceType();
    interface TransmissionDeviceTypeWithNameDTO {
        String getName();
    }
    SiteWithNameAndIdDTO getSite();
    interface SiteWithNameAndIdDTO {
        Float getId();
        String getSiteId();
        ProvinceWithName getProvince();
        interface ProvinceWithName {
            String getName();
        }
    }
    String getIp();
    String getNote();

}
