package com.backend.petshelter.security;

import com.backend.petshelter.dto.AccountSignIn;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.util.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountSecurityDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("La cuenta no fue encontrado:"+email));

        AccountSignIn accountSignIn = new AccountSignIn();
        accountSignIn.setEmail(account.getEmail());
        accountSignIn.setPassword(account.getPassword());
        accountSignIn.setToken(account.getToken());
        boolean active = account.isActive();
        accountSignIn.setActive(active);

        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(account.getRol().name()));

        return AccountPrincipal.builder()
                .account(accountSignIn)
                .id(account.getAccountUuid())
                .email(email)
                .password(account.getPassword())
                .authorities(authorities)
                .build();
    }
}