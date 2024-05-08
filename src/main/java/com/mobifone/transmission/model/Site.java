package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Entity
@SoftDelete
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siteOwnerId")
    private SiteOwner siteOwner;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siteTransmissionTypeId")
    private SiteTransmissionType siteTransmissionType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TransmissionOwnerId")
    private TransmissionOwner transmissionOwner;
    private String note;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provinceId",nullable = false)
    private Province province;
    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<LeaseLine> leaseLineList;

}
