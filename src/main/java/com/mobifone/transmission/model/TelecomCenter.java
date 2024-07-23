package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class TelecomCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "telecomCenter",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Province> provinces;

}
