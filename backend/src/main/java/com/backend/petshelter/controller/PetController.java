package com.backend.petshelter.controller;

import com.backend.petshelter.dto.PetDTO;
import com.backend.petshelter.model.Pet;
import com.backend.petshelter.service.implementation.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllActive(){
        List<PetDTO> listPet = petService.findByActivoTrue();
        return new ResponseEntity<>(listPet,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pet>> getAll(){
            List<Pet>petList= petService.getAll();
        return new ResponseEntity<>(petList,HttpStatus.OK);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<PetDTO>createPet(@RequestBody @Valid PetDTO petDTO){
        PetDTO pet1 = petService.createPet(petDTO);
       return new ResponseEntity<>(pet1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PetDTO>updatePet(@PathVariable Long id, @RequestBody PetDTO pet){
        PetDTO petDTO = petService.update(id,pet);
        return new ResponseEntity<>(petDTO,HttpStatus.OK);
    }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity<Void>deletePet(@PathVariable Long id){
        petService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

}
