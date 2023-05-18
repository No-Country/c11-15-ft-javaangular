import { TestBed } from '@angular/core/testing';

import { PetallService } from './petall.service';

describe('PetallService', () => {
  let service: PetallService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetallService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
