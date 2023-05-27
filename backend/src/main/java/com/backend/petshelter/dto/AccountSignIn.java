package com.backend.petshelter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountSignIn {
    private String email;
    private String password;
    private String token;
    private boolean active;
}