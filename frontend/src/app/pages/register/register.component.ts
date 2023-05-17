import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public register!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.register = this.formBuilder.group({
      fullname:[''],
      email:[''],
      password:['']
    })
  }

  registerUser(){
    this.http.post<any>("http://localhost:5000/registerUser", this.register.value)
    .subscribe(res => {
      alert("registrado exitosamente");
      this.register.reset();
      this.router.navigate(['login']);
    },err=>{
      alert("ocurrio un problema")
    })
  }
}
