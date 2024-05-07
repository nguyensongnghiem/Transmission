package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Router {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routerTypeId")
    private RouterType routerType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmissionDeviceTypeId",nullable = false)
    private TransmissionDeviceType transmissionDeviceType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SiteId",nullable = false)
    private Site site;
    @Column(nullable = false,unique = true)
    private String ip;
    private String status;
    private String note;

}



