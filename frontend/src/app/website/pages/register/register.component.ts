import { UsersService } from './../../../services/users.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
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

  constructor(
    private router: Router,
    private usersService: UsersService,
  ) {}

  ngOnInit(): void {
  }

  registerUser(data: any){
    console.log(data.value);
    this.usersService.create(data.value)
    .subscribe(res => {
      alert("registrado exitosamente");
      this.router.navigate(['login']);
    },err=>{
      alert('ocurrio un error')
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


