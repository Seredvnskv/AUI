import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Car} from '../model/car';

@Injectable({
  providedIn: 'root',
})
export class CarService {
  constructor(private http: HttpClient) {}

  getCars(): Observable<Car[]> {
    return this.http.get<Car[]>('/api/cars');
  }

  deleteCar(id: string): Observable<void> {
    return this.http.delete<void>(`/api/cars/${id}`)
  }
}
