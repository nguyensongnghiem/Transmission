package com.mobifone.transmission.model;

import jakarta.persistence.*;

@Entity
public class Router {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routerTypeId")
    private RouterType routerType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmissionDeviceTypeId",nullable = false)
    private TransmissionDeviceType transmissionDeviceType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SiteId",nullable = false)
    private Site site;
    @Column(nullable = false,unique = true)
    private String ip;
    private String status;
    private String note;

    public Router() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public void setRouterType(RouterType routerType) {
        this.routerType = routerType;
    }

    public TransmissionDeviceType getTransmissionDeviceType() {
        return transmissionDeviceType;
    }

    public void setTransmissionDeviceType(TransmissionDeviceType transmissionDeviceType) {
        this.transmissionDeviceType = transmissionDeviceType;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}



