import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MylistpetComponent } from './mylistpet.component';

describe('MylistpetComponent', () => {
  let component: MylistpetComponent;
  let fixture: ComponentFixture<MylistpetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MylistpetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MylistpetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
