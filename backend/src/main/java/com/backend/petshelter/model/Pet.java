package com.backend.petshelter.model;

import com.backend.petshelter.util.enums.NivelActividad;
import com.backend.petshelter.util.enums.Sex;
import com.backend.petshelter.util.enums.Tamaño;
import com.backend.petshelter.util.enums.Species;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nombre;
    private String foto;
    private String descripcion;
    private String cuidados;
    private String localidad;
    private String contacto;
    private String fechaDeNacimiento;
    private Boolean esterilizado;
    private Boolean desparacitado;
    private Boolean vacunado;
    private NivelActividad nivelActividad;
    private Tamaño tamaño;
    private Species especie;
    private Sex sex;
    private Boolean activo = true;

    public void borrar(){
        this.activo = false;
    }

}
