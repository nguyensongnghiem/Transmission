package com.mobifone.transmission.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class SiteTransmissionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "siteTransmissionType", cascade = CascadeType.ALL)
    private List<Site> siteList;

    public SiteTransmissionType() {
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
