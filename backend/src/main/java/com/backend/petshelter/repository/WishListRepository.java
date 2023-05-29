package com.backend.petshelter.repository;

import com.backend.petshelter.model.Account;
import com.backend.petshelter.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, String> {
    List<WishList> findByAccount(Account account);
}
