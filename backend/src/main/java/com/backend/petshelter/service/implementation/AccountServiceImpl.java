package com.backend.petshelter.service.implementation;

import com.backend.petshelter.dto.AccountDTO;
import com.backend.petshelter.dto.AccountDetailsDTO;
import com.backend.petshelter.dto.AccountRegistration;
import com.backend.petshelter.dto.PhonesDTO;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.model.AccountDetails;
import com.backend.petshelter.model.Phones;
import com.backend.petshelter.repository.AccountRepository;
import com.backend.petshelter.security.jwt.JwtProvider;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.util.enums.IdentificationType;
import com.backend.petshelter.util.enums.PhoneLabel;
import com.backend.petshelter.util.enums.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public AccountRegistration createAccountUserRol(Account account) {

        try {

            AccountRegistration accountRegistration = new AccountRegistration();
            String email = account.getEmail();
            accountRegistration.setEmail(email);
            String password = passwordEncoder.encode(account.getPassword());
            accountRegistration.setPassword(passwordEncoder.encode(password));

            Account saveAccount = new Account(email, password);
            accountRepository.save(saveAccount);
            String jwt = jwtProvider.generateToken(saveAccount);
            accountRegistration.setToken(jwt);

            return accountRegistration;

        } catch (Exception e) {
            throw new RuntimeException("Error creating account: " + e.getMessage());
        }
    }

    @Override
    public Optional<Account> findByEmail(String email) {
        try {
            if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        return accountRepository.findByEmail(email);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error finding account by email: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error finding account by email", e);
        }
    }

    @Transactional
    @Override
    public void changeRole(Role udpdateRole, String account) {
        try {
            if (udpdateRole == null) {
                throw new IllegalArgumentException("Role cannot be null");
            }

            if (account == null || account.isEmpty()) {
                throw new IllegalArgumentException("Account cannot be null or empty");
            }
            accountRepository.updateUserRole(account, udpdateRole);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error changing role: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error changing role", e);
        }
    }

    @Override
    public Account findByAccountReturnToken(String account) {
        Account email = accountRepository.findByEmail(account)
                .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + account));
        String jwt = jwtProvider.generateToken(email);
        email.setToken(jwt);
        return email;
    }

    @Transactional
    @Override
    public Account updateAccount(AccountDTO accountDTO) {
        try {
            Account existingAccount = accountRepository.findByEmail(accountDTO.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + accountDTO.getEmail()));

            AccountDetailsDTO accountDetailsDTO = accountDTO.getAccountDetails();
            AccountDetails accountDetails = existingAccount.getAccountDetails();

            if (accountDetails == null) {
                accountDetails = new AccountDetails();
                accountDetails.setAccountDetailsuuid(UUID.randomUUID().toString());
                accountDetails.setAccount(existingAccount);
            }

            // Validate if PhonesList is null or empty, and insert new phones if necessary
            List<Phones> phonesList = accountDetails.getPhonesList();
            if (phonesList == null || phonesList.isEmpty()) {
                phonesList = new ArrayList<>();
                accountDetails.setPhonesList(phonesList);

                for (PhonesDTO phoneDTO : accountDetailsDTO.getPhonesList()) {
                    Phones newPhone = new Phones();
                    newPhone.setPhoneUuid(UUID.randomUUID().toString());
                    newPhone.setPhoneLabel(phoneDTO.getPhoneLabel());
                    newPhone.setNumber(phoneDTO.getNumber());
                    newPhone.setCityCode(phoneDTO.getCityCode());
                    newPhone.setCountryCode(phoneDTO.getCountryCode());
                    phonesList.add(newPhone);
                }
            }

            // Update the phones list in AccountDetails
            accountDetails.setPhonesList(phonesList);

            BeanUtils.copyProperties(accountDetailsDTO, accountDetails, "accountDetailsuuid", "account", "phonesList");
            String identificationType = accountDetails.getIdentificationType().name();
            accountDetails.setIdentificationType(IdentificationType.valueOf(identificationType));

            // Update Phones
            List<Phones> updatedPhones = new ArrayList<>();
            Set<PhoneLabel> existingPhoneLabels = new HashSet<>();

            for (PhonesDTO phoneDTO : accountDetailsDTO.getPhonesList()) {
                PhoneLabel phoneLabel = phoneDTO.getPhoneLabel();
                String phoneNumber = phoneDTO.getNumber();

                Phones existingPhone = accountDetails.getPhonesList().stream()
                        .filter(phone -> phone.getPhoneLabel() == phoneLabel)
                        .findFirst()
                        .orElseGet(() -> {
                            Phones newPhone = new Phones();
                            newPhone.setPhoneUuid(UUID.randomUUID().toString());
                            newPhone.setPhoneLabel(phoneLabel);
                            existingPhoneLabels.add(phoneLabel);
                            return newPhone;
                        });

                existingPhone.setNumber(phoneNumber);
                existingPhone.setCityCode(phoneDTO.getCityCode());
                existingPhone.setCountryCode(phoneDTO.getCountryCode());
                updatedPhones.add(existingPhone);
            }

            // Remove phones with phoneLabels that no longer exist
            List<Phones> existingPhones = accountDetails.getPhonesList();
            existingPhones.removeIf(phone -> !existingPhoneLabels.contains(phone.getPhoneLabel()));

            accountDetails.setPhonesList(updatedPhones);

            existingAccount.setAccountDetails(accountDetails);

            return accountRepository.save(existingAccount);
        } catch (Exception e) {
            throw new RuntimeException("Error updating the account" + e.getMessage());
        }
    }
}
