import {ChangeDetectorRef, Component, OnInit } from '@angular/core';
import {Brand} from '../../model/brand';
import {BrandService} from '../../service/brand-service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-brand-edit-view',
  standalone: false,
  templateUrl: './brand-edit-view.html',
  styleUrls: ['./brand-edit-view.css'],
})
export class BrandEditView implements OnInit {
  constructor(private BrandService: BrandService, private route: ActivatedRoute, private router: Router, private cdr: ChangeDetectorRef) {}

  id: string | undefined;
  brand: Brand | undefined;

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.BrandService.getBrand(params['id']).subscribe(brand => {
        console.log(brand);
        this.brand = brand;
        this.id = this.brand.id;
        this.cdr.detectChanges();
      });
    })
  }

  onSubmit(): void {
    this.BrandService.updateBrand(this.id!, this.brand!).subscribe(() => this.router.navigate(['/brands']));
  }
}
