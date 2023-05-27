import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Mascota } from '../../../models/pet.model';

@Component({
  selector: 'app-pet',
  templateUrl: './pet.component.html',
  styleUrls: ['./pet.component.css']
})
export class PetComponent {
  @Input() mascota: Mascota = {
    id: '0',
    nombre: '',
    foto: '',
    descripcion: '',
    cuidados: '',
    localidad: '',
    contacto: 0,
    fechaDeNacimiento: '',
    esterilizado: false,
    desparacitado: false,
    vacunado: false,
    nivelActividad: '',
    size: '',
    especie: '',
    sex: '',
    activo: false
    }

  @Output() addedProduct = new EventEmitter<Mascota>();
  @Output() showdProduct = new EventEmitter<string>();

  onAddToCart() {
    this.addedProduct.emit(this.mascota);
  }

  onShowDetail() {
    this.showdProduct.emit(this.mascota.id)
  }
}
