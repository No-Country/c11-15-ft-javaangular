package com.backend.petshelter.controller;

import com.backend.petshelter.dto.AccountDTO;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.security.AccountPrincipal;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.util.enums.Role;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRol(@AuthenticationPrincipal AccountPrincipal accountPrincipal, @PathVariable Role role) {
        try {
            if (role == null) {
                throw new IllegalArgumentException("Invalid rol");
            }
            accountService.changeRole(role, accountPrincipal.getUsername());

            return ResponseEntity.ok(true);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/verify/{verificationCode}")
    public ResponseEntity<?> verifyAccount(@PathVariable String verificationCode) {
        try {
            if (verificationCode == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verification Failed");
            } else {
                boolean verified = accountService.verifyAccount(verificationCode);
                if (verified) {
                    return ResponseEntity.ok("Verification Succeeded");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Verification Failed");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred while verify the account" + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getCurrentAccount(@AuthenticationPrincipal AccountPrincipal accountPrincipal) {
        try {
            Account account = accountService.findByAccountReturnToken(accountPrincipal.getUsername());
            AccountDTO accountDTO = accountService.getCurrentAccount(account);
            return new ResponseEntity<>(accountDTO, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("updateAccount/{email}")
    public ResponseEntity<String> putUpdateAccount(@PathVariable String email, @Valid @RequestBody AccountDTO accountDTO) {
        try {
            accountDTO.setEmail(email);
            Account updatedAccount = accountService.updateAccount(accountDTO);
            if (updatedAccount != null) {
                return ResponseEntity.ok("Account updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update account");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the account" + e.getMessage());
        }
    }
}