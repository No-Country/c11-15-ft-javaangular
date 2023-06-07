import { AuthService } from 'src/app/services/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.css']
})
export class GridComponent {

  usuario: any[] = []

  constructor(
    private authService: AuthService
  ) {
    this.authService.listUser()
    .subscribe(user => {
      this.usuario = user
      console.log(this.usuario)
    }
      );
  }

}
