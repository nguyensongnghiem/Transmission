package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class TransmissionOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "transmissionOwner", cascade = CascadeType.ALL)
    private List<Site> siteList;
    @OneToMany(mappedBy = "transmissionOwner", cascade = CascadeType.ALL)
    private List<LeaseLine> leaseLineList;
}
