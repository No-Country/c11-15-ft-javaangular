package com.backend.petshelter.controller;

import com.backend.petshelter.dto.WishListDTO;
import com.backend.petshelter.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/{email}")
    public ResponseEntity<List<WishListDTO>> getWishListByEmail(@PathVariable String email) {
        try {
            List<WishListDTO> wishList = wishListService.getWishListByEmail(email);
            return ResponseEntity.ok(wishList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
