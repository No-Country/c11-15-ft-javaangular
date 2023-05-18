import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PetallComponent } from './petall.component';

describe('PetallComponent', () => {
  let component: PetallComponent;
  let fixture: ComponentFixture<PetallComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PetallComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PetallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
