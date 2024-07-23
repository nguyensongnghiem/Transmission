package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SoftDelete
@Entity
public class SiteTransmissionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "siteTransmissionType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Site> siteList;

}
