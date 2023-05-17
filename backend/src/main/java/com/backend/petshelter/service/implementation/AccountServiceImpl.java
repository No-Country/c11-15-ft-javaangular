package com.backend.petshelter.service.implementation;

import com.backend.petshelter.dto.AccountRegistration;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.repository.AccountRepository;
import com.backend.petshelter.security.jwt.JwtProvider;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.util.enums.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AccountRegistration createAccountUserRol(Account account){

        try {

            AccountRegistration accountRegistration = new AccountRegistration();
            String email = account.getEmail();
            accountRegistration.setEmail(email);
            String password = passwordEncoder.encode(account.getPassword());
            accountRegistration.setPassword(passwordEncoder.encode(password));

            Account saveAccount = new Account(email,password);
            accountRepository.save(saveAccount);
            String jwt = jwtProvider.generateToken(saveAccount);
            accountRegistration.setToken(jwt);

            return accountRegistration;

        }catch (Exception e){
            throw new RuntimeException("Error creating account: " + e.getMessage());
        }
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public void updateAccount(Account account){

        accountRepository.save(account);
    }
    @Transactional
    @Override
    public void changeRole(Role udpdateRole, String account){
        accountRepository.updateUserRole(account,udpdateRole);
    }

    @Override
    public Account findByAccountReturnToken(String account){
        Account email = accountRepository.findByEmail(account)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe" + account));
        String jwt = jwtProvider.generateToken(email);
        email.setToken(jwt);
        return email;
    }
}
