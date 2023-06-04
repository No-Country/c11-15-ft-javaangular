import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import { Mascota } from 'src/app/models/pet.model';
import { PetallService } from 'src/app/services/petall.service';
import Swiper, { Virtual } from 'swiper';

Swiper.use([Virtual])

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
      });
  }

  goToBack() {
    this.location.back();
  }
}
