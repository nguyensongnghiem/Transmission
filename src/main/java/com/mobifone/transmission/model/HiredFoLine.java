package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SoftDelete;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SoftDelete
public class HiredFoLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="foContractId")
    private FoContract foContract;
    private Integer coreQuantity;
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "nearSiteId")
    private Site nearSite;
    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "farSiteId")
    private Site farSite;
    private Integer cost;
    private float designedDistance;
    private float finalDistance;
    private String note;
}
