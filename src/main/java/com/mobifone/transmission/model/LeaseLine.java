package com.mobifone.transmission.model;

import jakarta.persistence.*;

@Entity
public class LeaseLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float speed;
    private float cost;
    private String note;
    private int quantity;
    private String connectType; // Kênh gom, Lasmile, Backup, Dự phòng

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

    public String getConnectType() {
        return connectType;
    }

    public void setConnectType(String connectType) {
        this.connectType = connectType;
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
