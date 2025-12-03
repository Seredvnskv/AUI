import { Component, OnInit } from '@angular/core';
import { BrandService } from '../../service/brand-service';
import { Brand } from '../../model/brand';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-brand-list',
  standalone: false,
  templateUrl: './brand-list.html',
  styleUrls: ['./brand-list.css'],
})
export class BrandList implements OnInit {
  constructor(private service: BrandService, private cdr: ChangeDetectorRef) {}

  brands: Brand[] = [];

  ngOnInit() {
    this.service.getBrands().subscribe(brands => {
      this.brands = brands;
      this.cdr.detectChanges();
    });
  }

  fetchBrands(): void {
    this.service.getBrands().subscribe(brands => {
      this.brands = brands;
      this.cdr.detectChanges();
    });
  }

  deleteBrand(id: string): void {
    this.service.deleteBrand(id).subscribe(() => {
      this.fetchBrands();
    });
  }
}
