package com.backend.petshelter.service;


import com.backend.petshelter.dto.PetDTO;
import com.backend.petshelter.model.Pet;

import java.util.List;

public interface IpetService{
    public Pet createPet(Pet pet);
    public PetDTO update(Long id, PetDTO pet);
    public List<Pet>getAll();
     public List<Pet> findByActivoTrue();

     public void delete(Long id);

}
