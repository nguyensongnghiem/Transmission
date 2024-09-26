package com.mobifone.transmission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SoftDelete
public class FoContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String contractNumber;

    private String contractName;
    @Column(nullable = false)
    private LocalDate signedDate;
    @Column(nullable = false)
    private LocalDate endDate;
    private boolean active = true;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transmissionOwnerId", nullable = false)
    private TransmissionOwner transmissionOwner;
    @OneToMany(mappedBy = "foContract", cascade = CascadeType.ALL)
    @JsonIgnore
    List<HiredFoLine> hiredFoLineList;
    private String note;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdOn;
    @UpdateTimestamp
    private Timestamp updatedOn;
}
