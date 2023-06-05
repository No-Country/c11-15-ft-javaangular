import { PasswordUser } from './../../../models/user.model';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomValidators } from 'ng2-validation';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css'],
})
export class ChangepasswordComponent {

  data: string | null;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private usersService: UsersService
  ) {
    this.data = (this.route.snapshot.paramMap.get('correo'));
  }
  email = new FormControl('')
  password = new FormControl('', Validators.required);
  certainPassword = new FormControl('', CustomValidators.equalTo(this.password));

  form = new FormGroup({
    email: this.email,
    password: this.password,
    certainPassword: this.certainPassword,
  });

  registerUser() {
    delete this.form.value.certainPassword
    console.log(this.form.value);
    this.form.value.email = this.data
    this.usersService.passwordRecord(<PasswordUser>this.form.value).subscribe(
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
