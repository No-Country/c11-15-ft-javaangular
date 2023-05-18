package com.backend.petshelter.service;

import com.backend.petshelter.model.Account;

public interface AuthenticationService {
    Account signInAndReturnJWT(Account signInRequest);
}
