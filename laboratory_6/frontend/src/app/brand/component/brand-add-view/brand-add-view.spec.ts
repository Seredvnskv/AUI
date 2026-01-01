import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrandAddView } from './brand-add-view';

describe('BrandAddView', () => {
  let component: BrandAddView;
  let fixture: ComponentFixture<BrandAddView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BrandAddView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrandAddView);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
