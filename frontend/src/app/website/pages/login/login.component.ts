import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  profile: User | null = null;

  email = new FormControl('', Validators.compose([Validators.required, Validators.email]))
  password = new FormControl('', Validators.required);

  constructor(
    private router: Router,
    private authService: AuthService,
  ){}

  form = new FormGroup({
    email: this.email,
    password: this.password
  })

  login(){
    console.log(this.form.value.email);
    this.authService.loginAndGet(this.form.value.email + '' , this.form.value.password + '')
    .subscribe(res=>{
      this.profile = res;
      if(res.email === this.profile.email && res.password === this.profile.password){
        alert('Login exitoso');
        this.router.navigate(['home'])
      }
    },error=>{
      alert('ocurrio un error')
    })
  }
}
