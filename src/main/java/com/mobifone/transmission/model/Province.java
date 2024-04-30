package com.mobifone.transmission.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Province {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private Set<Site> siteSet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecomCenterId", nullable = false)
    private  TelecomCenter telecomCenter;

    public Province() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Site> getSiteSet() {
        return siteSet;
    }

    public void setSiteSet(Set<Site> siteSet) {
        this.siteSet = siteSet;
    }

    public TelecomCenter getTelecomCenter() {
        return telecomCenter;
    }

    public void setTelecomCenter(TelecomCenter telecomCenter) {
        this.telecomCenter = telecomCenter;
    }
}
