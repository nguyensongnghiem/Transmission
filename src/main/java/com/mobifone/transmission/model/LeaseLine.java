package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@NoArgsConstructor
public class LeaseLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float speed;
    private float cost;
    private String note;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leaseLineConnectType",nullable = false)
    private LeaseLineConnectType leaseLineConnectType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmissionOwnerId",nullable = false)
    private TransmissionOwner transmissionOwner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "siteId",nullable = false)
    private Site site;

}
