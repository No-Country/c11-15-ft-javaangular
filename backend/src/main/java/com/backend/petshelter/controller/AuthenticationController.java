package com.backend.petshelter.controller;

import com.backend.petshelter.dto.AccountSignIn;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AccountService accountService;

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody Account account) {
        AccountSignIn signInAccount = authenticationService.signInAndReturnJWT(account);
        if (signInAccount.isActive() == false){
            return new ResponseEntity<>("Account is not active", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(signInAccount, HttpStatus.OK);
        }
    }

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody Account account) {
        try {
            if (account.getEmail() == null || account.getEmail().isEmpty()) {
                return new ResponseEntity<>("Email can't be empty", HttpStatus.BAD_REQUEST);
            }
            if (account.getPassword() == null || account.getPassword().isEmpty()) {
                return new ResponseEntity<>("Password can't be empty", HttpStatus.BAD_REQUEST);
            }
            if (account.getAccountDetails().getFullName() == null || account.getAccountDetails().getFullName().isEmpty()) {
                return new ResponseEntity<>("Fullname can't be empty", HttpStatus.BAD_REQUEST);
            }
            if (accountService.findByEmail(account.getEmail()).isPresent()) {
                return new ResponseEntity<>("This account already exists", HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(accountService.createAccountUserRol(account), HttpStatus.CREATED);
            }

        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while saving. Please check email format or password ", account);
            return new ResponseEntity<>(safeErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
