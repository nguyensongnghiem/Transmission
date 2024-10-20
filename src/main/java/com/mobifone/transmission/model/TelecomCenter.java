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
public class TelecomCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "telecomCenter",cascade = CascadeType.ALL)
    private Set<Province> provinces;

}
