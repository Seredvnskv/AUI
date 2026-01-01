import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarDetails } from './car-details';

describe('CarDetails', () => {
  let component: CarDetails;
  let fixture: ComponentFixture<CarDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarDetails]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarDetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
