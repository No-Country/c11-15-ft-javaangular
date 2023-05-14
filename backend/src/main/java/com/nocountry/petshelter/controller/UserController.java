package com.nocountry.petshelter.controller;

import com.nocountry.petshelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("findall")
    public ResponseEntity<?> getAllUsers(){

        return ResponseEntity.ok(userService.findAllUsers());
    }
}
