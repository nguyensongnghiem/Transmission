//package com.mobifone.transmission.mapper;
//
//import com.mobifone.transmission.dto.SiteViewDTO;
//import com.mobifone.transmission.model.Site;
//
//public class SiteMapper {
//    public static SiteViewDTO toSiteViewDTO(Site site) {
//        return SiteViewDTO.builder()
//                .id(site.getId())
//                .siteId(site.getSiteId())
//                .siteId2(site.getSiteId2())
//                .latitude(site.getLatitude())
//                .longitude(site.getLongitude())
//                .address(site.getAddress())
//                .siteOwner(site.getSiteOwner()==null?null:site.getSiteOwner().getName())
//                .siteTransmissionType(site.getSiteTransmissionType()==null?null:site.getSiteTransmissionType().getName())
//                .transmissionOwner(site.getTransmissionOwner()==null?null:site.getTransmissionOwner().getName())
//                .note(site.getNote())
//                .province(site.getProvince().getName())
//                .build();
//    }
//}
