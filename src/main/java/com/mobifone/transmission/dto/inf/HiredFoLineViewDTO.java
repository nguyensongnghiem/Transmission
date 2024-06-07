package com.mobifone.transmission.dto.inf;

import java.time.LocalDate;

public interface HiredFoLineViewDTO {
    int getId();
    Integer getCoreQuantity();
    String getContractName();
    LocalDate getSignedDate();
    LocalDate getEndDate();
    Integer getCost();
    float getDesignedDistance();
    float getFinalDistance();
    String getNote();
    FoContractWithContractNumber getFoContract();
    interface FoContractWithContractNumber {
        String getContractNumber();
    }
    SiteWithSiteId getNearSite();
    SiteWithSiteId getFarSite();

    interface SiteWithSiteId {
        String getSiteId();
    }


}
