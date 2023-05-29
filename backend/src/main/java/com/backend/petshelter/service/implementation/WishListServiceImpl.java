package com.backend.petshelter.service.implementation;

import com.backend.petshelter.dto.WishListDTO;
import com.backend.petshelter.model.Account;
import com.backend.petshelter.model.AccountDetails;
import com.backend.petshelter.model.Pet;
import com.backend.petshelter.model.WishList;
import com.backend.petshelter.repository.AccountRepository;
import com.backend.petshelter.repository.PetRepostory;
import com.backend.petshelter.repository.WishListRepository;
import com.backend.petshelter.service.WishListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService {
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PetRepostory petRepository;
    @Override
    @Transactional
    public WishListDTO addToWishList(String email, Long petId) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        Optional<Pet> petOptional = petRepository.findById(petId);

        if (accountOptional.isEmpty()) {
            throw new IllegalArgumentException("Account not found for the provided email.");
        }

        if (petOptional.isEmpty()) {
            throw new IllegalArgumentException("Pet not found for the provided ID.");
        }

        Account account = accountOptional.get();
        Pet pet = petOptional.get();
        AccountDetails accountDetails = account.getAccountDetails();

        if (accountDetails == null) {
            throw new IllegalArgumentException("Account details not found for the provided account.");
        }

        WishList wishList = new WishList();
        wishList.setAccount(account);
        wishList.setPet(pet);

        wishListRepository.save(wishList);

        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setPetName(pet.getNombre());
        wishListDTO.setFullName(accountDetails.getFullName());

        return wishListDTO;
    }
}
