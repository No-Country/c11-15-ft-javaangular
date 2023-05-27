package com.backend.petshelter.service;

import com.backend.petshelter.dto.AccountSignIn;
import com.backend.petshelter.model.Account;

public interface AuthenticationService {

    AccountSignIn signInAndReturnJWT(Account signInRequest);
}
