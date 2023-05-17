package com.backend.petshelter.controller;

import com.backend.petshelter.security.AccountPrincipal;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.util.enums.Role;
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
    public ResponseEntity<?> changeRol(@AuthenticationPrincipal AccountPrincipal accountPrincipal, @PathVariable Role role){
        accountService.changeRole(role, accountPrincipal.getUsername());

        return ResponseEntity.ok(true);
    }

    @GetMapping()
    public ResponseEntity<?> getCurrentAccount(@AuthenticationPrincipal AccountPrincipal accountPrincipal){
        return new ResponseEntity<>(accountService.findByAccountReturnToken(accountPrincipal.getUsername()), HttpStatus.OK);
    }
}
