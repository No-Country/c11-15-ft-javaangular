import { CreateUser, User } from 'src/app/models/user.model';
import { UsersService } from './../../../services/users.service';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { CustomValidators } from 'ng2-validation';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  constructor(private router: Router, private usersService: UsersService) {}

  email = new FormControl(
    '',
    Validators.compose([Validators.required, Validators.email])
  );
  name = new FormControl('', Validators.required);
  password = new FormControl('', Validators.required);
  certainPassword = new FormControl(
    '',
    CustomValidators.equalTo(this.password)
  );

  form = new FormGroup({
    email: this.email,
    name: this.name,
    password: this.password,
    certainPassword: this.certainPassword,
  });

  registerUser() {
    console.log(this.form.value);
    this.usersService.create({
      "email": this.form.value.email + "",
      "name": this.form.value.name + "",
      "password": this.form.value.password + ""
    }).subscribe(
      (res) => {
        alert('registrado exitosamente');
        this.router.navigate(['login']);
      },
      (err) => {
        alert('ocurrio un error');
      }
    );
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
