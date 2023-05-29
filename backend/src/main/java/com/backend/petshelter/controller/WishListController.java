package com.backend.petshelter.controller;

import com.backend.petshelter.dto.WishListDTO;
import com.backend.petshelter.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @PostMapping("/{email}/wishlist/{petId}")
    public ResponseEntity<WishListDTO> addToWishList(@RequestParam String email, @RequestParam Long petId) {
        try {
            WishListDTO wishListDTO = wishListService.addToWishList(email, petId);
            return ResponseEntity.ok(wishListDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
