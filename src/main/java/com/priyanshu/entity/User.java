package com.priyanshu.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private String role;

    @Column(nullable = false, updatable = false)
    private Instant registeredAt;


    @PrePersist
    public void registeredAt(){
        this.registeredAt = Instant.now();
    }

}
