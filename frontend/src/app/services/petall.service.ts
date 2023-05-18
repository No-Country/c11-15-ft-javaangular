import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Mascota } from '../models/pet.model';

@Injectable({
  providedIn: 'root'
})
export class PetallService {

  private apiUrl = 'https://young-sands-07814.herokuapp.com/api/products'

  constructor(
    private http: HttpClient
  ) { }

  getAllPetAll() {
    return this.http.get<Mascota[]>(this.apiUrl)
  }

  getPet(id:string) {
    return this.http.get<Mascota>(`${this.apiUrl}/${id}`)
  }
}
