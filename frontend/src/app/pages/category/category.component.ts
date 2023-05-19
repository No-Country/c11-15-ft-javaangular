import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Mascota } from 'src/app/models/pet.model';
import { PetallService } from 'src/app/services/petall.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  categoryId: string | null = null
  limit = 10;
  offset = 0;
  mascotas: Mascota[] = [];

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
        this.mascotas = data;
      });
  }

  onLoadMore() {
    if (this.categoryId) {
      this.petallService
        .getByCategory(this.categoryId, this.limit, this.offset)
        .subscribe((data) => {
          this.mascotas = this.mascotas.concat(data);
          this.offset += this.limit;
        });
    }
  }
}
