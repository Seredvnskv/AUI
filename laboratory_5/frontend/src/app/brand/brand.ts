import { Component} from '@angular/core';
import {BrandService} from './service/brand.service';
import {BrandModel} from './model/brand.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-brand',
  standalone: false,
  templateUrl: './brand.html',
  styleUrl: './brand.css',
})
export class Brand {
  brands_: Observable<BrandModel[]>;

  constructor(private service: BrandService) {
    this.brands_ = this.service.getBrands();
  }

  onDelete(brand: BrandModel) {
    console.log('Usuwam brand:', brand);
    console.log('ID:', brand.id);
    this.service.deleteBrand(brand.id);
    this.brands_ = this.service.getBrands();
  }
}
