package com.mobifone.transmission.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
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

}
