package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SoftDelete;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
@Builder
@AllArgsConstructor
@Setter
@Getter
@Entity
@SoftDelete
@NoArgsConstructor
public class Province {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Site> siteList;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telecomCenterId", nullable = false)
    private  TelecomCenter telecomCenter;

}
