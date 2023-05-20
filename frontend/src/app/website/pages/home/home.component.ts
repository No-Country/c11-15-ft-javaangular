import { Component } from '@angular/core';
import { PetallService } from 'src/app/services/petall.service';
import { Mascota } from 'src/app/models/pet.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  mascotas: Mascota[] = [];
  limit = 10;
  offset = 0;
  petId: string | null = null;

  constructor(
    private petallService: PetallService,
    private route: ActivatedRoute
  ) {}


  ngOnInit(): void {
    this.petallService.getPetByPage(10, 0).subscribe((data) => {
      this.mascotas = data;
      this.offset += this.limit;
    });
    this.route.queryParamMap.subscribe(params => {
      this.petId = params.get('product');
      console.log(this.petId);
    })
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
