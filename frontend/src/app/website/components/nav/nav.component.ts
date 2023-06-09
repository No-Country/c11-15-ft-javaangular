import { Component, OnInit } from '@angular/core';

import { StoreService } from '../../../services/store.service'
import { AuthService } from '../../../services/auth.service';
import { User } from '../../../models/user.model';
import { Category } from 'src/app/models/pet.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  activeMenu = false;
  counter = 0;
  profile: User | null = null;
  categories: Category[] = [];

  constructor(
    private storeService: StoreService,
    private authService: AuthService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.storeService.myPet$.subscribe(products => {
      this.counter = products.length;
    });
    this.authService.user$
    .subscribe(data => {
      this.profile = data
    })
  }

  toggleMenu() {
    this.activeMenu = !this.activeMenu;
  }

    login() {
    this.authService.loginAndGet('john@mail.com', 'changeme')
    .subscribe(() => {
      this.router.navigate(['/profile']);
    });
  }

  logout() {
    this.authService.logout();
    this.profile = null;
    this.router.navigate(['/home']);
  }

}
