package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(uniqueConstraints = {@UniqueConstraint(name = "user_role_UK", columnNames = {"userId", "roleId"})})
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

}
