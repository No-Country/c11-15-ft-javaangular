package com.backend.petshelter.controller;

import com.backend.petshelter.dto.WishListDTO;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.model.Pet;
import com.backend.petshelter.service.AccountService;
import com.backend.petshelter.service.WishListService;
import com.backend.petshelter.service.implementation.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PetService petService;
    @PostMapping("{email}/{petId}")
    public ResponseEntity<?> addToWishList(@PathVariable String email, @PathVariable Long petId) {
        try {
            Account account = accountService.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("The account does not exist." + email));

            Pet pet = petService.obtenerMascotaId(petId)
                    .orElseThrow(() -> new RuntimeException("Pet not found for the provided ID." + petId));
            boolean exist = wishListService.petExist(account, pet);
            if (exist == true){
                WishListDTO wishListDTO = wishListService.addToWishList(email, petId);
                return ResponseEntity.ok(wishListDTO);
            }else {
                return ResponseEntity.badRequest().body("The pet already exists in the wishlist.");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error request");
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
