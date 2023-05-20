import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  model = {
    email: '',
    password: '',
  }
  profile: User | null = null;

  public loginForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
  ){}

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email:['', [Validators.required, Validators.email]],
      password:['', [
            Validators.required,
            Validators.minLength(6),
            Validators.maxLength(20)
          ]]
    })
  }

  login(){
    this.authService.loginAndGet(this.model.email, this.model.password )
    .subscribe(res=>{
      this.profile = res;
      if(res.email === this.model.email && res.password === this.model.password){
        alert('Login exitoso');
        this.loginForm.reset();
        this.router.navigate(['home'])
      }
    },error=>{
      alert('ocurrio un error')
    })
  }
}
