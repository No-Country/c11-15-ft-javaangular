package com.backend.petshelter.model;

import com.backend.petshelter.util.enums.NivelActividad;
import com.backend.petshelter.util.enums.Sex;
import com.backend.petshelter.util.enums.Size;
import com.backend.petshelter.util.enums.Species;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Table(name = "PET")
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
    private Size size;
    private Species especie;
    private Sex sex;
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    private List<WishList> wishList;
    private Boolean activo = true;
    public void borrar(){
        this.activo = false;
    }

}
