import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import { concat } from 'rxjs';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
})
export class FormComponent {
  nombre = new FormControl('');
  especie = new FormControl('');
  sex = new FormControl('');
  vacunado = new FormControl(false);
  desparacitado = new FormControl(false);
  esterilizado = new FormControl(false);
  fechaDeNacimiento = new FormControl('');
  nivelActividad = new FormControl('');
  size = new FormControl('');
  descripcion = new FormControl('');
  cuidados = new FormControl('');
  pais = new FormControl('');
  estado = new FormControl('');
  depar = new FormControl('');
  localidad = new FormControl('');
  contacto = new FormControl();

  form = new FormGroup({
    nombre: this.nombre,
    especie: this.especie,
    sex: this.sex,
    vacunado: this.vacunado,
    desparacitado: this.desparacitado,
    esterilizado: this.esterilizado,
    fechaDeNacimiento: this.fechaDeNacimiento,
    nivelActividad: this.nivelActividad,
    size: this.size,
    descripcion: this.descripcion,
    cuidados: this.cuidados,
    pais: this.pais,
    estado: this.estado,
    depar: this.depar,
    localidad: this.localidad,
    contacto: this.contacto
  });

  registerPet(){
    this.form.value.localidad = this.form.value.pais + ', ';
    this.form.value.localidad = this.form.value.localidad + this.form.value.estado + ', ';
    this.form.value.localidad = this.form.value.localidad + this.form.value.depar + ' ';
    delete this.form.value.pais;
    delete this.form.value.estado;
    delete this.form.value.depar;
    console.log(this.form.value);
  }
}
