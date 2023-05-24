import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CustomValidators } from 'ng2-validation';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {

  password = new FormControl('', Validators.required);
  certainPassword = new FormControl('', CustomValidators.equalTo(this.password));

  form = new FormGroup({
  password: this.password,
  certainPassword: this.certainPassword
});
}
