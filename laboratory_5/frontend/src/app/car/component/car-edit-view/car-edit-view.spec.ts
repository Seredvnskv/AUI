import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarEditView } from './car-edit-view';

describe('CarEditView', () => {
  let component: CarEditView;
  let fixture: ComponentFixture<CarEditView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarEditView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarEditView);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
