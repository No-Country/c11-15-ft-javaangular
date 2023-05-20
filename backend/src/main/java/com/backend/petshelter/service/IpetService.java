package com.backend.petshelter.service;

import com.backend.petshelter.model.Pet;

import java.util.List;

public interface IpetService{
    public Pet createPet(Pet pet);
    public Pet update(Long id, Pet pet);
    public List<Pet>getAll();
    public void delete(Long id);
     public List<Pet> findByActivoTrue();

}
