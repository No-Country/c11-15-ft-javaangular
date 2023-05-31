package com.backend.petshelter.service;

import com.backend.petshelter.dto.WishListDTO;
import jakarta.transaction.Transactional;

import java.util.List;

public interface WishListService {
    @Transactional
    WishListDTO addToWishList(String email, Long petId);

    List<WishListDTO> getWishListByEmail(String email);
}
