import { Component, OnInit } from '@angular/core';
import {Brand} from '../../model/brand';
import {BrandService} from '../../service/brand-service';
import {ActivatedRoute, Router} from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';
import {Car} from '../../../car/model/car';
import {CarService} from '../../../car/service/car-service';

@Component({
  selector: 'app-brand-details',
  standalone: false,
  templateUrl: './brand-details.html',
  styleUrls: ['./brand-details.css'],
})
export class BrandDetails implements  OnInit {
  brand: Brand | undefined;
  cars: Car[] = [];

  constructor(private BrandService: BrandService, private CarService: CarService, private route: ActivatedRoute, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.fetchBrandsCars();
  }

  fetchBrandsCars(): void {
    this.route.params.subscribe(params => {
      this.BrandService.getBrand(params['id']).subscribe(brand => {
        this.brand = brand;
        this.cdr.detectChanges();
      });

      this.CarService.getCarsFromBrand(params['id']).subscribe(cars => {
        this.cars = cars;
        console.log(cars);
        this.cdr.detectChanges();
      });
    });
  }

  deleteCar(id: string): void {
    this.CarService.deleteCar(id).subscribe(() => {
      this.fetchBrandsCars();
    });
  }

}
