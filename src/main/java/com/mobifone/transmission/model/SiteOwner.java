package com.mobifone.transmission.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class SiteOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "siteOwner", cascade = CascadeType.ALL)
    private Set<Site> siteSet;

    public SiteOwner() {
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

    public Set<Site> getSiteSet() {
        return siteSet;
    }

    public void setSiteSet(Set<Site> siteSet) {
        this.siteSet = siteSet;
    }
}
