import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Brand } from './brand/brand';
import { Model } from './car/model';

const routes: Routes = [
  {
    path: "brands",
    component: Brand
  },
  {
    path: "models",
    component: Model
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
