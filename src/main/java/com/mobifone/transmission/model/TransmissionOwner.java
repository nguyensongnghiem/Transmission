package com.mobifone.transmission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
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
