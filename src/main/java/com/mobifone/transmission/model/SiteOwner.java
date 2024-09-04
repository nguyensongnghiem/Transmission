package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Builder
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SiteOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "siteOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Site> siteSet;
}
