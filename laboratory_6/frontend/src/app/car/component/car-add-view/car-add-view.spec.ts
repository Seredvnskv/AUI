import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarAddView } from './car-add-view';

describe('CarAddView', () => {
  let component: CarAddView;
  let fixture: ComponentFixture<CarAddView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarAddView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarAddView);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
