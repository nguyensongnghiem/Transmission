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
public class Province {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<Site> siteList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telecomCenterId", nullable = false)
    private  TelecomCenter telecomCenter;

}
