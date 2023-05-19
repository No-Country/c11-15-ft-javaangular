import { Component, OnInit } from '@angular/core';
import {
  CreateMascota,
  Mascota,
  UpdateMascota,
} from 'src/app/models/pet.model';
import { StoreService } from './../../services/store.service';
import { PetallService } from 'src/app/services/petall.service';

@Component({
  selector: 'app-petall',
  templateUrl: './petall.component.html',
  styleUrls: ['./petall.component.scss'],
})
export class PetallComponent implements OnInit {
  myStorePet: Mascota[] = [];
  total = 0;

  mascotas: Mascota[] = [];
  showProductDetail = false;
  petChosen: Mascota | null = null;

  limit = 10;
  offset = 0;

  statusDetail: 'loading' | 'success' | 'error' | 'init' = 'init';

  constructor(
    private storeService: StoreService,
    private petallService: PetallService
  ) {
    this.myStorePet = this.storeService.getStorePet();
  }

  ngOnInit(): void {
    this.petallService.getPetByPage(8, 0).subscribe((data) => {
      console.log(data);
      this.mascotas = data;
      this.offset += this.limit;
    });
  }

  onAddToShoppingCart(mascota: Mascota) {
    this.storeService.addProduct(mascota);
    this.total = this.storeService.getTotal();
    console.log(mascota);
  }

  toggleProductDetail() {
    this.showProductDetail = !this.showProductDetail;
  }

  onShowDetail(id: string) {
    this.statusDetail = 'loading';
    this.toggleProductDetail();
    this.petallService.getPet(id).subscribe(
      (data) => {
        console.log(data);
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
      title: 'nueva mascota',
      description: 'ingrsando una nueva mascota',
      images: [
        'https://cdn.shopify.com/s/files/1/0095/4253/3179/files/mobile-banne_-new.jpg?v=1614330110',
      ],
      price: 1200,
      categoryId: 2,
    };
    this.petallService.create(pet).subscribe((data) => {
      console.log('created', data);
      this.mascotas.unshift(data);
    });
  }

  updateMascota() {
    if (this.petChosen) {
      const changes: UpdateMascota = {
        title: 'mascota actualizada',
        description: 'esta mascota fue actulizada jejejejeej',
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

  loadMore() {
    this.petallService
      .getPetByPage(this.limit, this.offset)
      .subscribe((data) => {
        this.mascotas = this.mascotas.concat(data);
        this.offset += this.limit;
      });
  }
}
