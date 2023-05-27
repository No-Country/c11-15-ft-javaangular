package com.backend.petshelter.service;

import com.backend.petshelter.dto.AccountDTO;
import com.backend.petshelter.dto.AccountRegistration;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.util.enums.Role;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface AccountService {
    AccountRegistration createAccountUserRol(Account account);

    void sendVerificationCodeToEmail(Account account) throws MessagingException, UnsupportedEncodingException;

    Optional<Account> findByEmail(String email);

    @Transactional
    void changeRole(Role udpdateRole, String username);

    Account findByAccountReturnToken(String account);

    @Transactional
    Account updateAccount(AccountDTO accountDTO);

    AccountDTO getCurrentAccount(Account account);

    boolean verifyAccount(String verificationCode);
}
