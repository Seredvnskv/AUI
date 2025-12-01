import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BrandList} from './brand/component/brand-list/brand-list';
import {CarList} from './car/component/car-list/car-list';
import {BrandDetails} from './brand/component/brand-details/brand-details';
import {BrandEditView} from './brand/component/brand-edit-view/brand-edit-view';

const routes: Routes = [
  {
    path: "brands",
    component: BrandList
  },
  {
    path: "cars",
    component: CarList
  },
  {
    path: "brands/:id",
    component: BrandDetails
  },
  {
    path: "brands/:id/edit",
    component: BrandEditView
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
