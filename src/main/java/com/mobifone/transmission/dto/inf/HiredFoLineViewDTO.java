package com.mobifone.transmission.dto.inf;

import java.time.LocalDate;

public interface HiredFoLineViewDTO {
    int getId();
    Integer getCoreQuantity();
    Integer getCost();
    float getDesignedDistance();
    float getFinalDistance();
    String getNote();
    FoContractWithContractNumber getFoContract();
    interface FoContractWithContractNumber {
        int getId();
        String getContractNumber();
        String getContractName();
    }
    SiteWithSiteId getNearSite();
    SiteWithSiteId getFarSite();
    boolean getActive();
    interface SiteWithSiteId {
        int getId();
        String getSiteId();
        ProvinceViewDTO getProvince();
    }


}
