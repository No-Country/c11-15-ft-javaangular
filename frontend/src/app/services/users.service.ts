import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { User, CreateUser, RecoverUser, PasswordUser } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private apiUrl = environment.API_URL;

  constructor(private http: HttpClient) {}

  create(data: CreateUser) {
    return this.http.post<CreateUser>(`${this.apiUrl}/api/authentication/sign-up`, data);
  }

  recovery(data: RecoverUser) {
    return this.http.get<RecoverUser>(`${this.apiUrl}/api/account/passwordrecover/${data}`);
  }

  passwordRecord(data: PasswordUser) {
    return this.http.get<PasswordUser>(`${this.apiUrl}/api/account/passwordrecover/${data}`);
  }

  activacion(data: any) {
    return this.http.get<any>(`${this.apiUrl}/api/account/verify/${data}`)
  }
  /*  getAll() {
    return this.http.get<User[]>(this.apiUrl)
  } */
}
