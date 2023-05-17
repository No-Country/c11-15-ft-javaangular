package com.backend.petshelter.service;

import com.backend.petshelter.dto.AccountRegistration;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.util.enums.Role;
import jakarta.transaction.Transactional;

import java.util.Optional;

public interface AccountService {
    AccountRegistration createAccountUserRol(Account account);

    Optional<Account> findByEmail(String email);

    void updateAccount(Account account);

    @Transactional
    void changeRole(Role udpdateRole, String username);

    Account findByAccountReturnToken(String account);
}
