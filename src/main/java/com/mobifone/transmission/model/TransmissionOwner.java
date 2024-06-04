package com.mobifone.transmission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @OneToMany(mappedBy = "transmissionOwner", cascade = CascadeType.ALL)
    private List<Site> siteList;
    @JsonIgnore
    @OneToMany(mappedBy = "transmissionOwner", cascade = CascadeType.ALL)
    private List<LeaseLine> leaseLineList;
}
