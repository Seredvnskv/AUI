import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CarService} from '../../service/car-service';
import {ActivatedRoute, Router} from '@angular/router';
import {Car} from '../../model/car';
import {Brand} from '../../../brand/model/brand';
import {BrandService} from '../../../brand/service/brand-service';

@Component({
  selector: 'app-car-details',
  standalone: false,
  templateUrl: './car-details.html',
  styleUrls: ['./car-details.css'],
})
export class CarDetails implements OnInit {
  constructor(private CarService: CarService, private BrandService: BrandService, private route: ActivatedRoute, private router: Router, private cdr: ChangeDetectorRef) {}

  car : Car | undefined;
  carID : string | undefined;
  brand : Brand | undefined;
  brandID : string | undefined;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.BrandService.getBrand(params.get('brandID')!).subscribe(brand => {
        this.brand = brand;
        this.brandID = brand.id;
        this.cdr.detectChanges();
      });

      this.CarService.getCar(params.get('carID')!).subscribe(car => {
         this.car = car;
         this.carID = car.id;
        this.cdr.detectChanges();
      });
    });
  }
}
