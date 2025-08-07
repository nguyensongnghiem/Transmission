package com.mobifone.transmission.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.mobifone.transmission.dto.FoContractCreateDTO;
import com.mobifone.transmission.model.FoContract;
import com.mobifone.transmission.model.TransmissionOwner;
import com.mobifone.transmission.service.impl.TransmissionOwnerService;

@Mapper(componentModel = "spring")
public abstract class FoContractMapper {
    @Autowired
    private TransmissionOwnerService ownerService;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "active", constant = "true"),
            // @Mapping(target = "transmissionOwner", expression =
            // "java(findTransmissionOwnerById(createDTO.getTransmissionOwner()))")
    })
    public abstract FoContract toEntity(FoContractCreateDTO createDTO);

    // Phương thức helper để tìm đối tượng TransmissionOwner
    // protected TransmissionOwner findTransmissionOwnerById(Integer ownerId) {
    // if (ownerId == null) {
    // return null;
    // }
    // // Sử dụng repository để tìm đối tượng từ database
    // return ownerService.findById(ownerId)
    // .orElseThrow(() -> new RuntimeException("TransmissionOwner not found"));
    // }

}
