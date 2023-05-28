import { Component } from '@angular/core';
import { PetallService } from 'src/app/services/petall.service';
import { Mascota } from 'src/app/models/pet.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  mascota: Mascota[] = [];
  limit = 10;
  offset = 0;
  petId: string | null = null;

  constructor(
    private petallService: PetallService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.petallService.getAll().subscribe((data) => {
      this.mascota = data;
      this.offset += this.limit;
      console.log(this.mascota);
    });
/*     this.route.queryParamMap.subscribe((params) => {
      this.petId = params.get('product');
      console.log(this.petId);
    }); */
  }

  onLoadMore() {
    this.petallService.getAll(this.limit, this.offset).subscribe((data) => {
      this.mascota = this.mascota.concat(data);
      this.offset += this.limit;
    });
  }
}
