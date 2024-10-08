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
    }
    SiteWithSiteId getNearSite();
    SiteWithSiteId getFarSite();
    boolean getActive();
    interface SiteWithSiteId {
        String getSiteId();
    }


}
