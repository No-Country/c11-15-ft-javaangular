import { Component } from '@angular/core';
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
export class PetDetailComponent {
  petId: string | null = null;
  mascota: Mascota | null = null;

  constructor(
    private route: ActivatedRoute,
    private PetallService: PetallService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        switchMap((params) => {
          this.petId = params.get('id');
          if (this.petId) {
            return this.PetallService.getPet(this.petId);
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
