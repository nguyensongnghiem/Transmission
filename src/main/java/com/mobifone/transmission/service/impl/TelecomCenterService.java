package com.mobifone.transmission.service.impl;

import com.mobifone.transmission.model.TelecomCenter;
import com.mobifone.transmission.repository.ITelecomCenterRepository;
import com.mobifone.transmission.service.ITelecomCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TelecomCenterService implements ITelecomCenterService {


    @Autowired
    private ITelecomCenterRepository telecomCenterRepository;
    @Override
    public List<TelecomCenter> findAll() {
        return telecomCenterRepository.findAll();
    }
    @Override
    public boolean save(TelecomCenter telecomCenter) {
        return telecomCenterRepository.save(telecomCenter)!=null;
    }
}
