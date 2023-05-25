import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User, CreateUser } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private apiUrl = `${environment.API_URL}/api/authentication/sign-up`

  constructor(
    private http: HttpClient
  ) { }

  create(data: CreateUser) {
    return this.http.post<CreateUser>(this.apiUrl, data)
  }

 /*  getAll() {
    return this.http.get<User[]>(this.apiUrl)
  } */
}
