package com.backend.petshelter.dto;

import com.backend.petshelter.util.enums.NivelActividad;
import com.backend.petshelter.util.enums.Sex;
import com.backend.petshelter.util.enums.Species;
import com.backend.petshelter.util.enums.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PetDTO {

    @NotBlank
    private String nombre;
    private List<String> fotos;
    private String descripcion;
    private String cuidados;
    private String localidad;
    @NotBlank
    private String contacto;
    private String fechaDeNacimiento;
    private Boolean esterilizado;
    private Boolean desparacitado;
    private Boolean vacunado;
    private NivelActividad nivelActividad;
    private Size tamaño;
    private Species especie;
    private Sex sex;
    private Boolean activo = true;




}
