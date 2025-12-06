import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Car} from '../model/car';
import {CarCreateDTO} from '../model/carCreateDTO';

@Injectable({
  providedIn: 'root',
})
export class CarService {
  constructor(private http: HttpClient) {}

  getCars(): Observable<Car[]> {
    return this.http.get<Car[]>('/api/cars');
  }

  getCarsFromBrand(id: string): Observable<Car[]> {
    return this.http.get<Car[]>(`/api/cars/brand/${id}/models`);
  }

  getCar(id: string): Observable<Car> {
    return this.http.get<Car>(`api/cars/${id}`);
  }

  addCar(car: CarCreateDTO): Observable<Car> {
    return this.http.post<Car>('/api/cars', car);
  }

  updateCar(id: string, car: Car): Observable<Car> {
    return this.http.patch<Car>(`api/cars/${id}`, car);
  }


  deleteCar(id: string): Observable<void> {
    return this.http.delete<void>(`/api/cars/${id}`)
  }
}
