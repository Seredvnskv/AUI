import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { Brand } from '../model/brand'

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  constructor(private http: HttpClient) {}

  getBrands(): Observable<Brand[]> {
    return this.http.get<Brand[]>('/api/brands');
  }

  getBrand(id: string): Observable<Brand> {
    return this.http.get<Brand>(`api/brands/${id}`);
  }

  updateBrand(id: string, brand: Brand): Observable<Brand> {
    return this.http.patch<Brand>(`api/brands/${id}`, brand);
  }

  deleteBrand(id: string): Observable<void> {
    return this.http.delete<void>(`/api/brands/${id}`)
  }
}
