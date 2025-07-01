package com.example.learn2025.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(
        name = "first_name"
    )
    private String firstName;

    @Column(
        name = "last_name"
    )
    private String lastName;

    @Column(unique = true)
    private String account;

    private String password;
}
