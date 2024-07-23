package com.mobifone.transmission.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@SoftDelete
@Entity
public class RouterType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendorId", nullable = false)
    private Vendor vendor;

    @OneToMany(mappedBy = "routerType",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Router> routers;

}
