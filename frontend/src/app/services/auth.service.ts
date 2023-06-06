import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { switchMap, tap } from 'rxjs/operators';

import { environment } from './../../environments/environment';
import { Auth } from './../models/auth.model';
import { User } from './../models/user.model';
import { TokenService } from './../services/token.service';
import { BehaviorSubject } from 'rxjs';
import { Mascota } from '../models/pet.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = `${environment.API_URL}/api`;
  private user = new BehaviorSubject<User | null>(null);

  user$ = this.user.asObservable();

  constructor(
    private http: HttpClient,
    private tokenService: TokenService
  ) { }

  login(email: string, password: string) {
    return this.http.post<Auth>(`${this.apiUrl}/authentication/sign-in`, {email, password})
    .pipe(
      tap(response => this.tokenService.saveToken(response.token))
    );
  }

  getProfile() {
    return this.http.get<User>(`${this.apiUrl}/account`)
    .pipe(
      tap(user => this.user.next(user))
    );
  }

  loginAndGet(email: string, password: string) {
    return this.login(email, password)
    .pipe(
      switchMap(() => this.getProfile()),
    )
  }

  logout() {
    this.tokenService.removeToken();
  }

  listUser() {
    return this.http.get<any>(`${this.apiUrl}/account/findallaccountlist`);
  }
}
