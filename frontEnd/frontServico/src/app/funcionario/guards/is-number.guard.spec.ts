import { TestBed } from '@angular/core/testing';

import { IsNumberGuard } from './is-number.guard';

describe('IsNumberGuard', () => {
  let guard: IsNumberGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(IsNumberGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
