package com.lahee.security.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    //제약 사항
    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    private String email;
    private String phone;

}
