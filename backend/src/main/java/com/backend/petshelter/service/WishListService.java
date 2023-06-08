package com.backend.petshelter.service;

import com.backend.petshelter.dto.WishListDTO;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.model.Pet;
import jakarta.transaction.Transactional;

import java.util.List;

public interface WishListService {
    @Transactional
    WishListDTO addToWishList(String email, Long petId);

    void petExist(Account account, Pet pet);

    List<WishListDTO> getWishListByEmail(String email);
}
