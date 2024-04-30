package com.mobifone.transmission.service;

import com.mobifone.transmission.model.TelecomCenter;

import java.util.List;

public interface ITelecomCenterService {
    public List<TelecomCenter> findAll();
    public boolean save(TelecomCenter telecomCenter);

}
