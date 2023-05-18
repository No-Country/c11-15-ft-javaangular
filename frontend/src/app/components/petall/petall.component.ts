import { Component, OnInit } from '@angular/core';
import { Mascota } from 'src/app/models/pet.model';
import { StoreService } from './../../services/store.service';
import { PetallService } from 'src/app/services/petall.service';

@Component({
  selector: 'app-petall',
  templateUrl: './petall.component.html',
  styleUrls: ['./petall.component.scss']
})
export class PetallComponent implements OnInit {

  myStorePet: Mascota[] = [];
  total = 0;

  mascotas: Mascota[] = [];
  showProductDetail = false;
  petChosen: Mascota = {
    id: '',
    price: 0,
    images: [],
    title: '',
    description: '',
    category: {
      id: '',
      name: ''
  }
}

  constructor(
    private storeService: StoreService,
    private petallService: PetallService
  ) {
    this.myStorePet = this.storeService.getStorePet();
  }

  ngOnInit(): void {
    this.petallService.getAllPetAll()
    .subscribe(data => {
      console.log(data);
      this.mascotas = data;
    })
  }

  onAddToShoppingCart(mascota: Mascota) {
    this.storeService.addProduct(mascota);
    this.total = this.storeService.getTotal();
    console.log(mascota);
  }

  toggleProductDetail(){
    this.showProductDetail = !this.showProductDetail;
  }

  onShowDetail(id: string){
    this.petallService.getPet(id)
    .subscribe(data =>{
      console.log(data)
      this.toggleProductDetail();
      this.petChosen = data;
    })
  }
}
