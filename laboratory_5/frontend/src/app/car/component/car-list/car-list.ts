import { Component, OnInit } from '@angular/core';
import {CarService} from '../../service/car-service';
import {Car} from '../../model/car';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-car-list',
  standalone: false,
  templateUrl: './car-list.html',
  styleUrl: './car-list.css',
})
export class CarList implements OnInit {
  constructor(private service: CarService, private cdr: ChangeDetectorRef) {}

  cars: Car[] = [];

  ngOnInit() {
    this.fetchCars();
  }

  fetchCars(): void {
    this.service.getCars().subscribe(cars => {
      this.cars = cars;
      this.cdr.detectChanges();
    });
  }

  deleteCar(id: string): void {

  }
}
