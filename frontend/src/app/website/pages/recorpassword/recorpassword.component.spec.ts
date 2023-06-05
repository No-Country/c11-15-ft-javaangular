import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecorpasswordComponent } from './recorpassword.component';

describe('RecorpasswordComponent', () => {
  let component: RecorpasswordComponent;
  let fixture: ComponentFixture<RecorpasswordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecorpasswordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecorpasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
