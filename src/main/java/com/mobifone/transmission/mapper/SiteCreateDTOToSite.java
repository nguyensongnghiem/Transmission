package com.mobifone.transmission.mapper;

import com.mobifone.transmission.dto.*;
import com.mobifone.transmission.model.*;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class SiteCreateDTOToSite implements Function<SiteCreateDTO, Site> {

    @Override
    public Site apply(SiteCreateDTO siteDTO) {
        return Site.builder()
                .id(siteDTO.getId())
                .siteId(siteDTO.getSiteId())
                .siteId2(siteDTO.getSiteId2())
                .siteName(siteDTO.getSiteName())
                .address(siteDTO.getAddress())
                .latitude(siteDTO.getLatitude())
                .longitude(siteDTO.getLongitude())
                .siteOwner(siteDTO.getSiteOwner()!=null?SiteOwner.builder()
                        .id(siteDTO.getSiteOwner().getId())
                        .name(siteDTO.getSiteOwner().getName())
                        .build():null)
                .siteTransmissionType(SiteTransmissionType.builder()
                        .id(siteDTO.getSiteTransmissionType().getId())
                        .name(siteDTO.getSiteTransmissionType().getName())
                        .build())
                .transmissionOwner(TransmissionOwner.builder()
                        .id(siteDTO.getTransmissionOwner().getId())
                        .name(siteDTO.getTransmissionOwner().getName())
                        .build())
                .province(Province.builder()
                        .id(siteDTO.getProvince().getId())
                        .name(siteDTO.getProvince().getName())
                        .build())
                .note(siteDTO.getNote())
                .build();
    }
}
