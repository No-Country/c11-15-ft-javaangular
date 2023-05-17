package com.backend.petshelter.security.jwt;

import com.backend.petshelter.model.Account;
import com.backend.petshelter.security.AccountPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(AccountPrincipal auth);

    String generateToken(Account account);

    Authentication getAuthentication(HttpServletRequest request);

    boolean isTokenValid(HttpServletRequest request);
}
