package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false,precision = 10, scale = 5)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 10, scale = 5)
    private BigDecimal longitude;

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
    @JsonIgnore
    private List<LeaseLine> leaseLineList;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Router> routerList;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp updatedOn;
}
