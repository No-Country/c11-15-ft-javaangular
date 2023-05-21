import { UsersService } from './../../../services/users.service';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  model = {
      name: '',
      email: '',
      password: '',
    }

  private apiUrl = `${environment.API_URL}/api/user`;

  public register!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private usersService: UsersService,
  ) {}

  ngOnInit(): void {
    this.register = this.formBuilder.group({
      fullname:[''],
      email:[''],
      password:['']
    })
  }

  registerUser(){
    this.usersService.create(this.model)
    .subscribe(res => {
      alert("registrado exitosamente");
      this.register.reset();
      this.router.navigate(['login']);
    },err=>{
      alert(this.register.contains.arguments)
    })
    /* this.http.post<any>(this.apiUrl, this.register.value)
    .subscribe(res => {
      alert("registrado exitosamente");
      this.register.reset();
      this.router.navigate(['login']);
    },err=>{
      alert(this.register.contains.arguments)
    }) */
  }
}
