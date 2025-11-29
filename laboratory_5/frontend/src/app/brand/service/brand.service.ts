import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BrandModel} from '../model/brand.model';

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  constructor(private http: HttpClient) {}

  findAllBrands(): Observable<BrandModel[]> {
    return this.http.get<BrandModel[]>('/api/brands')
  }
}
