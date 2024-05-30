package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(name = "user_role_UK", columnNames = {"userId", "roleId"})})
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

}
