import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BrandModel} from '../model/brand.model';

@Injectable({
  providedIn: 'root',
})
export class BrandService {
  constructor(private http: HttpClient) {}

  getBrands(): Observable<BrandModel[]> {
    return this.http.get<BrandModel[]>('/api/brands')
  }

  deleteBrand(id: string): Observable<void> {
    return this.http.delete<void>(`/api/brands/${id}`);
  }
}
