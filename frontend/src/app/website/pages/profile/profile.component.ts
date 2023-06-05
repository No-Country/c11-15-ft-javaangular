import { StoreService } from './../../../services/store.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User | null = null;

  constructor(
    private authService: AuthService,
    private storeService: StoreService,
  ) {
    console.log(this.data)
   }


  data = this.storeService.getStorePet()

  ngOnInit(): void {
    this.authService.user$
    .subscribe(data => {
      this.user = data;
    })
  }

}
