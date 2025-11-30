import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { App } from './app';

import { Footer } from './component/footer/footer';
import { Main } from './component/main/main';
import { Header } from './component/header/header';
import { BrandList } from './brand/component/brand-list/brand-list';
import { CarList } from './car/component/car-list/car-list';
import { CarDetails } from './car/component/car-details/car-details';
import { BrandDetails } from './brand/component/brand-details/brand-details';

@NgModule({
  declarations: [
    App,
    Footer,
    Main,
    Header,
    BrandList,
    CarList,
    CarDetails,
    BrandDetails
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(withInterceptorsFromDi()),
  ],
  bootstrap: [App]
})
export class AppModule { }
