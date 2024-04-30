package com.mobifone.transmission.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class TransmissionDeviceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "transmissionDeviceType",cascade = CascadeType.ALL)
    private Set<Router> routers;

    public TransmissionDeviceType() {
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

    public Set<Router> getRouters() {
        return routers;
    }

    public void setRouters(Set<Router> routers) {
        this.routers = routers;
    }
}
