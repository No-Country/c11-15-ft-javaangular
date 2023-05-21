package com.backend.petshelter.dto;

import com.backend.petshelter.model.Pet;
import com.backend.petshelter.util.enums.Sex;
import com.backend.petshelter.util.enums.Tamaño;
import com.backend.petshelter.util.enums.Species;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PetDTO {

 @NotBlank
 private String nombre;
 private String foto;
 private String caracteristicas;
 private String cuidados;
 @NotBlank
 private String contacto;
 private String fechaDeNacimiento;
 private String nivelActividad;
 private Boolean esterilizado;
 private Boolean vacunado;
 private Tamaño tamaño;
 private Species especie;
 private Sex sex;
 private Boolean activo;




}
