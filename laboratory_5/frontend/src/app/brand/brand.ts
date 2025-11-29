import { Component, OnInit } from '@angular/core';
import {BrandService} from './service/brand.service';
import {BrandModel} from './model/brand.model';

@Component({
  selector: 'app-brand',
  standalone: false,
  templateUrl: './brand.html',
  styleUrl: './brand.css',
})
export class Brand implements OnInit{
  constructor(private service: BrandService) {
  }

  brands: BrandModel[] | undefined;

  ngOnInit() {
    this.service.findAllBrands()
      .subscribe(brands => this.brands = brands)
  }
}
