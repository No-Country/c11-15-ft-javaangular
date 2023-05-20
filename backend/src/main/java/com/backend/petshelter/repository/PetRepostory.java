package com.backend.petshelter.repository;

import com.backend.petshelter.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PetRepostory extends JpaRepository<Pet, Long> {

    List<Pet> findByActivoTrue();
}
