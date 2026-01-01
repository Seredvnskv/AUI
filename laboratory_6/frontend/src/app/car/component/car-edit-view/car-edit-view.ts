import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {CarService} from '../../service/car-service';
import {ActivatedRoute, Router} from '@angular/router';
import {Car} from '../../model/car';

@Component({
  selector: 'app-car-edit-view',
  standalone: false,
  templateUrl: './car-edit-view.html',
  styleUrls: ['./car-edit-view.css'],
})
export class CarEditView implements OnInit {
  constructor(private CarService: CarService, private route: ActivatedRoute, private router: Router, private cdr: ChangeDetectorRef) {}

  car: Car | undefined;
  carID: string | undefined;
  brandID: string | undefined;

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.brandID = params.get('brandID')!;

      this.CarService.getCar(params.get('carID')!).subscribe(car => {
        this.car = car;
        this.carID = this.car.id;
        this.cdr.detectChanges();
      });
    });
  }

  onSubmit() {
    this.CarService.updateCar(this.carID!, this.car!).subscribe(() => this.router.navigate(['/brands', this.brandID, 'details']));
  }
}
