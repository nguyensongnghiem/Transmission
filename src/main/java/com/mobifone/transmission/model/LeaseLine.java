package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
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

    public LeaseLine() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LeaseLineConnectType getLeaseLineConnectType() {
        return leaseLineConnectType;
    }

    public void setLeaseLineConnectType(LeaseLineConnectType leaseLineConnectType) {
        this.leaseLineConnectType = leaseLineConnectType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TransmissionOwner getTransmissionOwner() {
        return transmissionOwner;
    }

    public void setTransmissionOwner(TransmissionOwner transmissionOwner) {
        this.transmissionOwner = transmissionOwner;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
