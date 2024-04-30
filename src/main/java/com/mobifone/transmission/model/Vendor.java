package com.mobifone.transmission.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private Set<RouterType> RouterTypes;

    public Vendor() {
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

    public Set<RouterType> getRouterTypes() {
        return RouterTypes;
    }

    public void setRouterTypes(Set<RouterType> routerTypes) {
        RouterTypes = routerTypes;
    }
}
