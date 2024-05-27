package com.mobifone.transmission.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
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

    public User(String username, String email, String password, State state) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.state = state;
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.state = State.PENDING;
    }
}
    