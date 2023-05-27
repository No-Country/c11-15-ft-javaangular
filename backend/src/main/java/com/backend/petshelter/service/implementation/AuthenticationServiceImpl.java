package com.backend.petshelter.service.implementation;

import com.backend.petshelter.dto.AccountSignIn;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.security.AccountPrincipal;
import com.backend.petshelter.security.jwt.JwtProvider;
import com.backend.petshelter.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AccountSignIn signInAndReturnJWT(Account signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );

        AccountPrincipal authenticationPrincipal = (AccountPrincipal) authentication.getPrincipal();

        String jwt = jwtProvider.generateToken(authenticationPrincipal);

        AccountSignIn signInUser = authenticationPrincipal.getAccount();
        signInRequest.setActive(authenticationPrincipal.getAccount().isActive());
        signInUser.setToken(jwt);

        return signInUser;
    }
}
