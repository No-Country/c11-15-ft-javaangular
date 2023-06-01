import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import { Mascota } from 'src/app/models/pet.model';
import { PetallService } from 'src/app/services/petall.service';

@Component({
  selector: 'app-pet-detail',
  templateUrl: './pet-detail.component.html',
  styleUrls: ['./pet-detail.component.scss']
})
export class PetDetailComponent implements OnInit {
  productId: string | null = null;
  mascota: Mascota | null = null;

  constructor(
    private route: ActivatedRoute,
    private productsService: PetallService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        switchMap((params) => {
          this.productId = params.get('id');
          if (this.productId) {
            return this.productsService.getOne(this.productId);
          }
          return [null];
        })
      )
      .subscribe((data) => {
        this.mascota = data;
        console.log(this.mascota?.fotos[1])
      });
  }

  goToBack() {
    this.location.back();
  }
}
