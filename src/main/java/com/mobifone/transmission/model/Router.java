package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

@Setter
@Getter
@NoArgsConstructor
@SoftDelete
@Entity
public class Router {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "routerTypeId")
    private RouterType routerType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transmissionDeviceTypeId",nullable = false)
    private TransmissionDeviceType transmissionDeviceType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SiteId",nullable = false)
    private Site site;
    @Column(nullable = false,unique = true)
    private String ip;
    private String note;

}



