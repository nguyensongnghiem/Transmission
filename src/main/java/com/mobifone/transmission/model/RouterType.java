package com.mobifone.transmission.model;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class RouterType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "vendorId", nullable = false)
    private Vendor vendor;

    @OneToMany(mappedBy = "routerType",cascade = CascadeType.ALL)
    private Set<Router> routers;
    public RouterType() {
    }

    public Set<Router> getRouters() {
        return routers;
    }

    public void setRouters(Set<Router> routers) {
        this.routers = routers;
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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
