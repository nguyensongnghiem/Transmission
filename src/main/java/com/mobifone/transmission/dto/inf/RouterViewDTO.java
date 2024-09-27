package com.mobifone.transmission.dto.inf;
public interface RouterViewDTO {
    Long getId();
    String getName();
    RouterTypeWithNameDTO getRouterType();
    interface RouterTypeWithNameDTO {
        int getId();
        String getName();
    }
    TransmissionDeviceTypeWithNameDTO getTransmissionDeviceType();
    interface TransmissionDeviceTypeWithNameDTO {
        int getId();
        String getName();
    }
    SiteWithNameAndIdDTO getSite();
    interface SiteWithNameAndIdDTO {
        Float getId();
        String getSiteId();
        ProvinceWithName getProvince();
        interface ProvinceWithName {
            String getId();
            String getName();
        }
    }
    String getIp();
    String getNote();

}
