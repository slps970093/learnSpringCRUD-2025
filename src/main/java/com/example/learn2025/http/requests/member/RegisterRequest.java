package com.example.learn2025.http.requests.member;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String name;

    private String firstName;

    private String lastName;

    private String account;

    private String password;
}
