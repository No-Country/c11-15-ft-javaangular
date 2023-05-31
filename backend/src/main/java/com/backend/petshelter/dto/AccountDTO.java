package com.backend.petshelter.dto;

import com.backend.petshelter.model.Account;
import com.backend.petshelter.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String uuid;
    private String email;
    private String password;
    private String rol;
    private AccountDetailsDTO accountDetails;
    private String token;
}