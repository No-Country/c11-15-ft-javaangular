package com.backend.petshelter.service.implementation;

import com.backend.petshelter.model.Pet;
import com.backend.petshelter.repository.PetRepostory;
import com.backend.petshelter.service.IpetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PetService implements IpetService {
   @Autowired
    private PetRepostory petRepostory;

    @Override
    public Pet createPet(Pet pet) {
     return petRepostory.save(pet);
    }

    @Override
    public List<Pet> findByActivoTrue() {
        return petRepostory.findByActivoTrue();
    }

    @Override
    public List<Pet>getAll(){
        return petRepostory.findAll();
    }
    @Override
    public Pet update(Long id, Pet pet) {
      Pet editado = petRepostory.getReferenceById(id);
           editado.setNombre(pet.getNombre());
           editado.setFoto(pet.getFoto());
           editado.setCaracteristicas(pet.getCaracteristicas());
           editado.setCuidados(pet.getCuidados());
           editado.setContacto(pet.getContacto());
           return editado;
       }



    @Override
    public void delete(Long id) {
    Pet pet = petRepostory.getReferenceById(id);
    pet.borrar();
    }




}
