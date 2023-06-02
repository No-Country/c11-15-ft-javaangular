import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-autenticacion',
  templateUrl: './autenticacion.component.html',
  styleUrls: ['./autenticacion.component.css']
})
export class AutenticacionComponent {

  constructor( private route: ActivatedRoute){
    console.log(this.route.snapshot.paramMap.get('clave'))
  }
}
