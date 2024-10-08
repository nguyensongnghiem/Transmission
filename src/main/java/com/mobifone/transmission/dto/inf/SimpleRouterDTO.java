package com.mobifone.transmission.dto.inf;

public interface SimpleRouterDTO {
    Long getId();
    String getName();
    String getRouterType();
    String getTransmissionDeviceType();
    boolean getActive();
    String getSiteId();
}
