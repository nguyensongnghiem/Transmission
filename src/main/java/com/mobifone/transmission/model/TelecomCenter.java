package com.mobifone.transmission.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class TelecomCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "telecomCenter",cascade = CascadeType.ALL)
    private Set<Province> provinces;
    public TelecomCenter() {
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

    public Set<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }
}
