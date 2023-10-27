import { TestBed } from '@angular/core/testing';

import { BackServicesService } from './back-services.service';

describe('BackServicesService', () => {
  let service: BackServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BackServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
