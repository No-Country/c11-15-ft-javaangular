import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
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
    private router: Router,
    private authService: AuthService,
  ){}

  ngOnInit(): void {
  }

  login(data: any){
    console.log(data, 'hola')
    this.authService.loginAndGet(data['email'], data['password'] )
    .subscribe(res=>{
      this.profile = res;
      if(res.email === this.model.email && res.password === this.model.password){
        alert('Login exitoso');
        this.router.navigate(['home'])
        this.loginForm.reset();
      }
    },error=>{
      alert('ocurrio un error')
    })
  }
}
