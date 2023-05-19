import { Component } from '@angular/core';
import { PetallService } from 'src/app/services/petall.service';
import { Mascota } from 'src/app/models/pet.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  mascotas: Mascota[] = [];
  limit = 10;
  offset = 0;

  constructor(
    private petallService: PetallService
  ) {}

    ngOnInit(): void {
    this.petallService.getPetByPage(8, 0).subscribe((data) => {
      console.log(data);
      this.mascotas = data;
      this.offset += this.limit;
    });
  }

  onLoadMore() {
    this.petallService
      .getPetByPage(this.limit, this.offset)
      .subscribe((data) => {
        this.mascotas = this.mascotas.concat(data);
        this.offset += this.limit;
      });
  }
}
