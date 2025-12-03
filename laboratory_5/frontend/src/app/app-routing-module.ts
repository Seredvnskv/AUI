import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BrandList} from './brand/component/brand-list/brand-list';
import {CarList} from './car/component/car-list/car-list';
import {BrandDetails} from './brand/component/brand-details/brand-details';
import {BrandEditView} from './brand/component/brand-edit-view/brand-edit-view';
import {BrandAddView} from './brand/component/brand-add-view/brand-add-view';

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
    path: "brands/add",
    component: BrandAddView
  },
  {
    path: "brands/:id/edit",
    component: BrandEditView
  },
  {
    path: "brands/:id",
    component: BrandDetails
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
