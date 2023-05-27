import { Component, OnInit } from '@angular/core';
import { UsersService } from './services/users.service';
import { AuthService } from './services/auth.service';
import { TokenService } from './services/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  imgParent = '';
  showImg = true;
  token = '';
  imgRta = '';

  constructor(
    private usersService: UsersService,
    private tokenService: TokenService,
    private AuthService: AuthService
  ) {

  }

  ngOnInit(): void {
    const token = this.tokenService.getToken();
    if (token) {
      this.AuthService.getProfile()
      .subscribe()
    }
  }

  onLoaded(img: string) {
    console.log('log padre', img);
  }

  toggleImg() {
    this.showImg = !this.showImg;
  }

  createUser() {
    this.usersService.create({
      name: 'sebas',
      email: 'sebas1@mail.com',
      password: '11212'
    })
    .subscribe(rta => {
      console.log(rta);
    });
  }
}
