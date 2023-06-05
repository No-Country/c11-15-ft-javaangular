package com.backend.petshelter.controller;

import com.backend.petshelter.dto.AccountDTO;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.security.AccountPrincipal;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.util.enums.Role;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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
    @GetMapping("findallcustomerlist")
    public ResponseEntity<?> getAllUsersDetails(@AuthenticationPrincipal AccountPrincipal accountPrincipal){
        Account account = accountService.findByAccountReturnToken(accountPrincipal.getUsername());
        if(account.getRol().name() == Role.ADMIN.name()){
            return ResponseEntity.ok(accountService.findAllAccountList());
        }else {
            return ResponseEntity.badRequest().body("you don't have permission");
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

    @GetMapping("passwordrecover/{email}")
    public ResponseEntity<?> passwordRecovery(@PathVariable String email) {
        try {
            if (email == null || email.isEmpty()) {
                return ResponseEntity.badRequest().body("Email can't be empty");
            }
            Account buscarEmail = accountService.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("This account does not exist." + email));

            accountService.sendPasswordRecoveryToEmail(buscarEmail);
            return ResponseEntity.ok("Password recovery email sent successfully");

        } catch (MessagingException | UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while sending the password recovery email");
        } catch (Exception e) {
            String safeErrorMessage = "Error while recovery. Please check email format";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(safeErrorMessage);
        }
    }
}