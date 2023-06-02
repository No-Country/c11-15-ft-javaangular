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
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<Pet>> getAllActive(){
        List<Pet> listPet = petService.findByActivoTrue();
        return new ResponseEntity<>(listPet,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pet>>getPetForId(@PathVariable Long id){
        Optional<Pet> petOk = petService.obtenerMascotaId(id);
        return new ResponseEntity<>(petOk,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Pet>> getAll(){
            List<Pet>petList= petService.getAll();
        return new ResponseEntity<>(petList,HttpStatus.OK);
    }
    @PostMapping
    @Transactional
    public ResponseEntity<Pet>createPet(@RequestBody @Valid Pet pet){
        Pet petCreada = petService.createPet(pet);
       return new ResponseEntity<>(petCreada, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PetDTO>updatePet(@PathVariable Long id, @RequestBody @Valid PetDTO pet){
        PetDTO petEditada = petService.update(id,pet);
        return new ResponseEntity<>(petEditada,HttpStatus.OK);
    }

        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity<Void>deletePet(@PathVariable Long id){
        petService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

}
