import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrandEditView } from './brand-edit-view';

describe('BrandEditView', () => {
  let component: BrandEditView;
  let fixture: ComponentFixture<BrandEditView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BrandEditView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BrandEditView);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
