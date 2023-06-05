import { UsersService } from 'src/app/services/users.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-autenticacion',
  templateUrl: './autenticacion.component.html',
  styleUrls: ['./autenticacion.component.css']
})
export class AutenticacionComponent implements OnInit {

  data: string | null;
  constructor(
    private route: ActivatedRoute,
    private usersService: UsersService
    ){
    this.data = this.route.snapshot.paramMap.get('codeVerification');
  }

  ngOnInit(): void {
    this.usersService.activacion(this.data);
  }

}
