import { Component } from '@angular/core';
import {Brand} from '../../model/brand';
import {BrandService} from '../../service/brand-service';
import {BrandCreateDTO} from '../../model/brandCreateDTO';
import {Router} from '@angular/router';

@Component({
  selector: 'app-brand-add-view',
  standalone: false,
  templateUrl: './brand-add-view.html',
  styleUrls: ['./brand-add-view.css'],
})
export class BrandAddView {
  constructor(private BrandService: BrandService, private router: Router) {}

  brand: Brand | undefined;
  name: string | undefined;
  country: string | undefined;
  establishmentDate: number | undefined;

  onSubmit(): void {
    const newBrand : BrandCreateDTO = {name: this.name!, country: this.country!, establishmentDate: this.establishmentDate!};
    this.BrandService.addBrand(newBrand).subscribe(createdBrand => {
      this.brand = createdBrand;
      console.log(this.brand);
      this.router.navigate(['/brands']);
    });
  }

  onReset(): void {
    this.name = '';
    this.country = '';
    this.establishmentDate = undefined;
    this.brand = undefined;
  }
}
