package com.backend.petshelter.model;

import com.backend.petshelter.util.enums.Sex;
import com.backend.petshelter.util.enums.Tamaño;
import com.backend.petshelter.util.enums.Species;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String foto;
    private String caracteristicas;
    private String cuidados;

    private String contacto;
    private String fechaDeNacimiento;
    private String nivelActividad;
    private Boolean esterilizado;
    private Boolean vacunado;
    private Tamaño tamaño;
    private Species especie;
    private Sex sex;

    private Boolean activo = true;

    public void borrar(){
        this.activo = false;
    }

}
