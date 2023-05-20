package com.backend.petshelter.repository;

import com.backend.petshelter.model.Account;
import com.backend.petshelter.util.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);

    Optional<Account> findByAccountUuid(String uuid);
    @Modifying
    @Query("update Account set rol=:rol where email=:email")
    void updateUserRole(@Param("email") String username, @Param("rol") Role rol);
}
