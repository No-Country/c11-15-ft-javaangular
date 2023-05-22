package com.backend.petshelter.service.implementation;

import com.backend.petshelter.dto.PetDTO;
import com.backend.petshelter.model.Pet;
import com.backend.petshelter.repository.PetRepostory;
import com.backend.petshelter.service.IpetService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class PetService implements IpetService {
   @Autowired
    private PetRepostory petRepostory;

   @Autowired
   private ModelMapper modelMapper;

    @Override
    public PetDTO createPet(PetDTO petdto) {
     Pet pet = modelMapper.map(petdto, Pet.class);
     petRepostory.save(pet);
      return petdto;
    }

    @Override
    public List<PetDTO> findByActivoTrue() {
        List<Pet>listPet = petRepostory.findByActivoTrue();
        List<PetDTO>petDTOList = listPet.stream()
                .map(pet -> {
                    PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
                    pet.setId(pet.getId());
                    return petDTO;
                }).collect(Collectors.toList());
        return petDTOList;
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
      List<PetDTO>petDTOList = petList.stream()
              .map(pet-> modelMapper.map(pet, PetDTO.class)).collect(Collectors.toList());

        return petList;
    }
    @Override
    public PetDTO update(Long id, PetDTO petDto) {
        Pet petEncontrada = petRepostory.getReferenceById(id);
        if (petEncontrada != null) {   //valida que exista
            modelMapper.map(petDto, petEncontrada); // convierte el DTO  a pet ecnontrada de clase Pet
            petRepostory.save(petEncontrada);  //guarda en la bdd
        }
        PetDTO petDtoActualizado = modelMapper.map(petEncontrada, PetDTO.class);  //convierte Pet a dto para devolverlo
        return petDtoActualizado;
    }


}


