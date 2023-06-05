import { Injectable } from '@angular/core';
import { Mascota } from '../models/pet.model';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StoreService {
  private myStorePet: Mascota[] = [];
  private myPet = new BehaviorSubject<Mascota[]>([]);

  myPet$ = this.myPet.asObservable();

  constructor() {}

  addProduct(mascota: Mascota) {
    if (this.myStorePet.find((test) => test.id === mascota.id) === undefined) {
      this.myStorePet.push(mascota), this.myPet.next(this.myStorePet);
    }
  }

  getStorePet() {
    return this.myStorePet;
  }

  getTotal() {
    return this.myStorePet.reduce((sum, item) => sum + item.contacto, 0);
  }
}
