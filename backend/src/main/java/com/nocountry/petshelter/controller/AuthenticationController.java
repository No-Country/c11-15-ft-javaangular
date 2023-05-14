package com.nocountry.petshelter.controller;

import com.nocountry.petshelter.model.account.User;
import com.nocountry.petshelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<?> singUp(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUserAccount(user), HttpStatus.CREATED);

    }
}
