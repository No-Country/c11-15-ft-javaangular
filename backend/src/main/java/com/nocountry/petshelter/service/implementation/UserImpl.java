package com.nocountry.petshelter.service.implementation;

import com.nocountry.petshelter.model.account.Rol;
import com.nocountry.petshelter.model.account.User;
import com.nocountry.petshelter.repository.UserRepository;
import com.nocountry.petshelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUserAccount(User user){
        var userId = UUID.randomUUID().toString();

        user.setUserId(userId);
        user.setCreatedDate(LocalDateTime.now());
        user.setRol(Rol.USER);
        user.setActive(user.isActive());

        User createUser = userRepository.save(user);

        return createUser;
    }

    @Override
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
