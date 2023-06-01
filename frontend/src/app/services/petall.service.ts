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

  private apiUrl = `${environment.API_URL}`;

  constructor(
    private http: HttpClient
  ) { }

  getByCategory(categoryId: string, limit?: number, offset?: number){
    let params = new HttpParams();
    if (limit && offset != null) {
      params = params.set('limit', limit);
      params = params.set('offset', offset);
    }
    return this.http.get<Mascota[]>(`${this.apiUrl}/categories/${categoryId}/products`, { params })
  }

  getAll(limit?: number, offset?: number) {
    let params = new HttpParams();
    if (limit && offset != null) {
      params = params.set('limit', limit);
      params = params.set('offset', offset);
    }
    return this.http.get<Mascota[]>(`${this.apiUrl}/pet/all`)
    .pipe(
      retry(3),
      map(products => products.map(item => {
        return {
          ...item,
          taxes: .19 * item.contacto
        }
      }))
    );
  }

  fetchReadAndUpdate(id: string, dto: UpdateMascota) {
    return zip(
      this.getOne(id),
      this.update(id, dto)
    );
  }

  getOne(id: string) {
    return this.http.get<Mascota>(`${this.apiUrl}/pet/${id}`)
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

  create(dto: CreateMascota) {
    return this.http.post<Mascota>(`${this.apiUrl}/pet`, dto);
  }

  update(id: string, dto: UpdateMascota) {
    return this.http.put<Mascota>(`${this.apiUrl}/pet/${id}`, dto);
  }

  delete(id: string) {
    return this.http.delete<boolean>(`${this.apiUrl}/pet/${id}`);
  }
}
