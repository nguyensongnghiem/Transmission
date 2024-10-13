package com.mobifone.transmission.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    
    @Column(nullable = false,unique = true,length = 30)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,length = 100)
    private String password;
    private State state;
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    List<UserRole> userRoles;

    public UserEntity(String username, String email, String password, State state) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.state = state;
    }
    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.state = State.PENDING;
    }
}
    