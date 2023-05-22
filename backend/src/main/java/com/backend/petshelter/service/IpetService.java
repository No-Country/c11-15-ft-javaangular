package com.backend.petshelter.service;

import com.backend.petshelter.dto.PetDTO;
import com.backend.petshelter.model.Pet;

import java.util.List;

public interface IpetService{
    public PetDTO createPet(PetDTO pet);
    public PetDTO update(Long id, PetDTO pet);
    public List<Pet>getAll();
     public List<PetDTO> findByActivoTrue();

     public void delete(Long id);

}
