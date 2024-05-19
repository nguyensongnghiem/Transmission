package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SourceType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
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

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<Router> routerList;


}
