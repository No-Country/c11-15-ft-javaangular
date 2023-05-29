package com.backend.petshelter.service;

import com.backend.petshelter.dto.WishListDTO;
import jakarta.transaction.Transactional;

public interface WishListService {
    @Transactional
    WishListDTO addToWishList(String email, Long petId);
}
