import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { CreateMascota, Mascota, UpdateMascota } from '../models/pet.model';
import { retry } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PetallService {

  private apiUrl = `${environment.API_URL}/api/products`

  constructor(
    private http: HttpClient
  ) { }

  getAllPetAll(limit?: number, offset?: number) {
    let params = new HttpParams();
    if (limit && offset) {
      params = params.set('limit', limit);
      params = params.set('offset', limit);
    }
    return this.http.get<Mascota[]>(this.apiUrl, { params })
    .pipe(
      retry(3)
    );
  }

  getPet(id:string) {
    return this.http.get<Mascota>(`${this.apiUrl}/${id}`)
  }

  getPetByPage(limit: number, offset: number) {
    return this.http.get<Mascota[]>(`${this.apiUrl}`, {
      params: { limit, offset }
    })
  }

  create(data: CreateMascota) {
    return this.http.post<Mascota>(this.apiUrl, data);
  }

  update(id: string, data: UpdateMascota) {
    return this.http.put<Mascota>(`${this.apiUrl}/${id}`, data)
  }

  delete(id: string) {
    return this.http.delete<boolean>(`${this.apiUrl}/${id}`)
  }
}
