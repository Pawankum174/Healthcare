import { TestBed } from '@angular/core/testing';


import { JwtInterceptor } from './auth-interceptor';

describe('authInterceptor', () => {
  const interceptor = JwtInterceptor;

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });
});

export default {JwtInterceptor};