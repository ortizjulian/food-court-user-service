package com.restaurant.user_service.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleEntity;

}
