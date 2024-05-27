package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Data;
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
public class LeaseLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float speed;
    private float cost;
    private String note;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "leaseLineConnectType",nullable = false)
    private LeaseLineConnectType leaseLineConnectType;
    @ManyToOne
    @JoinColumn(name = "transmissionOwnerId",nullable = false)
    private TransmissionOwner transmissionOwner;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "siteId",nullable = false)
    private Site site;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp updatedOn;

}
