import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { CreateMascota, Mascota, UpdateMascota } from '../models/pet.model';
import { retry, catchError, map } from 'rxjs/operators';
import { throwError, zip } from 'rxjs';
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

  fetchReadAndUpdate(id: string, dto: UpdateMascota) {
    return zip(
      this.getPet(id),
      this.update(id, dto)
    );
  }

  getPet(id: string) {
    return this.http.get<Mascota>(`${this.apiUrl}/${id}`)
    .pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === HttpStatusCode.Conflict) {
          return throwError('Algo esta fallando en el server');
        }
        if (error.status === HttpStatusCode.NotFound) {
          return throwError('El producto no existe');
        }
        if (error.status === HttpStatusCode.Unauthorized) {
          return throwError('No estas permitido');
        }
        return throwError('Ups algo salio mal');
      })
    )
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
