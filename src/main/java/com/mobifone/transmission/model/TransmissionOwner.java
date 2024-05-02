package com.mobifone.transmission.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TransmissionOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "transmissionOwner", cascade = CascadeType.ALL)
    private List<Site> siteList;

    @OneToMany(mappedBy = "transmissionOwner", cascade = CascadeType.ALL)
    private List<LeaseLine> leaseLineList;
    public TransmissionOwner() {
    }

    public List<LeaseLine> getLeaseLineList() {
        return leaseLineList;
    }

    public void setLeaseLineList(List<LeaseLine> leaseLineList) {
        this.leaseLineList = leaseLineList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }
}
