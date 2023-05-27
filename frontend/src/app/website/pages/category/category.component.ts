import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Mascota } from 'src/app/models/pet.model';
import { PetallService } from 'src/app/services/petall.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent {
/*   categoryId: string | null = null;
  limit = 10;
  offset = 0;
  mascota: Mascota[] = [];
  petId: string | null = null;

  constructor(
    private route: ActivatedRoute,
    private petallService: PetallService
  ) {}

  ngOnInit(): void {
    this.route.paramMap
      .pipe(
        switchMap((params) => {
          this.categoryId = params.get('id');
          if (this.categoryId) {
            return this.petallService.getByCategory(
              this.categoryId,
              this.limit,
              this.offset
            );
          }
          return [];
        })
      )
      .subscribe((data) => {
        this.mascota = data;
        this.route.queryParamMap.subscribe((params) => {
          this.petId = params.get('product');
          console.log(this.petId);
        });
      });
  }

  onLoadMore() {
    if (this.categoryId) {
      this.petallService
        .getByCategory(this.categoryId, this.limit, this.offset)
        .subscribe((data) => {
          this.mascota = this.mascota.concat(data);
          this.offset += this.limit;
        });
    }
  } */
}
