import { Component, OnInit } from '@angular/core';
import {Brand} from '../../model/brand';
import {BrandService} from '../../service/brand-service';
import {ActivatedRoute, Router} from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-brand-details',
  standalone: false,
  templateUrl: './brand-details.html',
  styleUrl: './brand-details.css',
})
export class BrandDetails implements  OnInit {
  brand: Brand | undefined;

  constructor(private service: BrandService, private route: ActivatedRoute, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getBrand(params['id']).subscribe(brand => {
        this.brand = brand;
        console.log(brand);
        this.cdr.detectChanges();
      });
    });
  }
}
