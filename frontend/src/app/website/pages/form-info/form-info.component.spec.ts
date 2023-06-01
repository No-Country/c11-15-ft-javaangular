import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormInfoComponent } from './form-info.component';

describe('FormInfoComponent', () => {
  let component: FormInfoComponent;
  let fixture: ComponentFixture<FormInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
