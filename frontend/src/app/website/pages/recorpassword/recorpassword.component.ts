import { Component } from '@angular/core';
import { CreateUser, User } from 'src/app/models/user.model';
import { UsersService } from './../../../services/users.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { CustomValidators } from 'ng2-validation';

@Component({
  selector: 'app-recorpassword',
  templateUrl: './recorpassword.component.html',
  styleUrls: ['./recorpassword.component.css']
})
export class RecorpasswordComponent {
  constructor(private router: Router, private usersService: UsersService) {}

  email = new FormControl(
    '',
    Validators.compose([Validators.required, Validators.email])
  );

  form = new FormGroup({
    email: this.email,
  });

  registerUser() {
    console.log(this.form.value);
    this.usersService.recovery({
      "email": this.form.value.email + ""
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
