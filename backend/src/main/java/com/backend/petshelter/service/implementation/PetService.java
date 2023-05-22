package com.backend.petshelter.service.implementation;


import com.backend.petshelter.dto.PetDTO;
import com.backend.petshelter.model.Pet;
import com.backend.petshelter.repository.PetRepostory;
import com.backend.petshelter.service.IpetService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.util.List;


@Service
@Validated
public class PetService implements IpetService {
   @Autowired
    private PetRepostory petRepostory;

   @Autowired
   private ModelMapper modelMapper;

    @Override
    public Pet createPet(Pet pet) {
     /*Pet pet = modelMapper.map(petdto, Pet.class);*/
     petRepostory.save(pet);
      return pet;
    }

    @Override
    public List<Pet> findByActivoTrue() {
        List<Pet>listPet = petRepostory.findByActivoTrue();
        return listPet;
          /* List<PetDTO>petDTOList = listPet.stream()
                .map(pet -> {
                    PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
                    pet.setId(pet.getId());
                    return petDTO;
                }).collect(Collectors.toList()); */
    }

    @Override
    public void delete(Long id) {
       Pet petEncontrada = petRepostory.getReferenceById(id);
            if(petEncontrada != null){
            petEncontrada.borrar();
        }
    }

    @Override
    public List<Pet>getAll(){
      List<Pet> petList = petRepostory.findAll();
     /* List<PetDTO>petDTOList = petList.stream()
              .map(pet-> modelMapper.map(pet, PetDTO.class)).collect(Collectors.toList()); */
        return petList;
    }
    @Override
    public PetDTO update(Long id, PetDTO pet) {
        Pet petEncontrada = petRepostory.getReferenceById(id);
        if(petEncontrada != null){
           modelMapper.map(pet,petEncontrada);
           petRepostory.save(petEncontrada);
        }
       PetDTO petActualizado = modelMapper.map(petEncontrada, PetDTO.class);
        return petActualizado;
    }
}

