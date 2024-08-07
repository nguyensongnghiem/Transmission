package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String name;

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
        @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp updatedOn;

}



