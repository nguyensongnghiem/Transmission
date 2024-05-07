package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Entity
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(nullable = false,unique = true)
    private String siteId;
    private String siteId2;
    private String siteName;
    @Column(nullable = false)
    private Float latitude;
    @Column(nullable = false)
    private Float longitude;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "siteOwnerId")
    private SiteOwner siteOwner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "siteTransmissionTypeId")
    private SiteTransmissionType siteTransmissionType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TransmissionOwnerId")
    private TransmissionOwner transmissionOwner;
    private boolean status;
    private String note;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId",nullable = false)
    private Province province;
    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<LeaseLine> leaseLineList;

}
