import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  CreateMascota,
  Mascota,
  UpdateMascota,
} from 'src/app/models/pet.model';
import { StoreService } from '../../../services/store.service';
import { PetallService } from 'src/app/services/petall.service';
import Swiper, { Virtual } from 'swiper';

Swiper.use([Virtual])

@Component({
  selector: 'app-petall',
  templateUrl: './petall.component.html',
  styleUrls: ['./petall.component.scss'],
})
export class PetallComponent {
  myStorePet: Mascota[] = [];
  total = 0;

  @Input() mascotas: Mascota[] = [];
  @Input()
  set petId(id: string | null) {
    if (id) {
      this.onShowDetail(id);
    }
  }
  @Output() loadMore = new EventEmitter();
  showProductDetail = false;
  petChosen: Mascota | null = null;

  statusDetail: 'loading' | 'success' | 'error' | 'init' = 'init';

  constructor(
    private storeService: StoreService,
    private petallService: PetallService
  ) {
    this.myStorePet = this.storeService.getStorePet();
  }

  onAddToShoppingCart(mascota: Mascota) {
    this.storeService.addProduct(mascota);
    this.total = this.storeService.getTotal();
  }

  toggleProductDetail() {
    this.showProductDetail = !this.showProductDetail;
  }

  onShowDetail(id: string) {
    this.statusDetail = 'loading';
    if (!this.showProductDetail) {
      this.showProductDetail = true;
    }
    this.petallService.getOne(id).subscribe(
      (data) => {
        this.petChosen = data;
        this.statusDetail = 'success';
      },
      (errorMsg) => {
        window.alert(errorMsg);
        this.statusDetail = 'error';
      }
    );
  }

  createNewProduct() {
    const pet: CreateMascota = {
      nombre: 'labrador',
      fotos: ['https://cdn.shopify.com/s/files/1/0095/4253/3179/files/mobile-banne_-new.jpg?v=1614330110'],
      descripcion: 'ingrsando una nueva mascota',
      cuidados: 'no requiere cuidados',
      localidad: 'Buenos Aires, Argentina',
      contacto: 310125,
      esterilizado: true,
      desparacitado: true,
      vacunado: true,
      nivelActividad: 'ALTO',
      size: null,
      especie: 'PERRO',
      sex: 'MALE',
      fechaDeNacimiento: ''
    };
    this.petallService.create(pet).subscribe((data) => {
      console.log('created', data);
      this.mascotas.unshift(data);
    });
  }

  updateMascota() {
    if (this.petChosen) {
      const changes: UpdateMascota = {
        nombre: 'mascota actualizada',
        descripcion: 'esta mascota fue actulizada jejejejeej',
      };
      const id = this.petChosen?.id;
      this.petallService.update(id, changes).subscribe((data) => {
        console.log('update', data);
        const mascotaIndex = this.mascotas.findIndex(
          (item) => item.id === this.petChosen?.id
        );
        this.mascotas[mascotaIndex] = data;
        this.petChosen = data;
      });
    }
  }

  deletePet() {
    if (this.petChosen) {
      const id = this.petChosen?.id;
      this.petallService.delete(id).subscribe(() => {
        const mascotaIndex = this.mascotas.findIndex(
          (item) => item.id === this.petChosen?.id
        );
        this.mascotas.splice(mascotaIndex, 1);
        this.showProductDetail = false;
      });
    }
  }

  onloadMore() {
    this.loadMore.emit();
  }
}
