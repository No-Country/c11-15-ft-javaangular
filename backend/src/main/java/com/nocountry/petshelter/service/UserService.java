package com.nocountry.petshelter.service;

import com.nocountry.petshelter.model.account.User;

import java.util.List;

public interface UserService {
    User createUserAccount(User user);

    List<User> findAllUsers();
}
