package com.mobifone.transmission.dto.inf;

import com.mobifone.transmission.model.LeaseLineConnectType;

public interface LeaseLineViewDTO {
    int getId();
    Float getSpeed();
    Float getCost();
    String getNote();
    int getQuantity();
    LeaseLineConnectTypeWithNameDTO getLeaseLineConnectType();
    interface LeaseLineConnectTypeWithNameDTO {
        String getName();
    }
    TransmissionOwnerWithNameDTO getTransmissionOwner();
    interface TransmissionOwnerWithNameDTO {
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



}
