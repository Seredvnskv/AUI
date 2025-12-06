import { Component, OnInit } from '@angular/core';
import {CarService} from '../../service/car-service';
import {ActivatedRoute, Router} from '@angular/router';
import {CarCreateDTO} from '../../model/carCreateDTO';
import {Car} from '../../model/car';

@Component({
  selector: 'app-car-add-view',
  standalone: false,
  templateUrl: './car-add-view.html',
  styleUrl: './car-add-view.css',
})
export class CarAddView implements OnInit {
  constructor(private CarService: CarService, private router: Router, private route: ActivatedRoute) {}

  car: Car | undefined;
  brandId: string | null | undefined;
  model: string | undefined;
  productionYear: number | undefined;
  price: number | undefined;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.brandId = params.get('id');
      console.log(this.brandId);
    });
  }

  onSubmit() {
    const newCar: CarCreateDTO = {brandId: this.brandId!, model: this.model!, price: this.price!, productionYear: this.productionYear!};
    this.CarService.addCar(newCar).subscribe(createdCar => {
      this.car = createdCar;
      console.log(this.car);
      this.router.navigate([`/brands/${this.brandId}/details`]);
    });
  }

  onReset(): void {
    this.model = '';
    this.productionYear = undefined;
    this.price = undefined;
    this.car = undefined;
  }
}
