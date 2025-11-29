import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { HttpClientModule } from '@angular/common/http';
import { App } from './app';

import { Footer } from './component/footer/footer';
import { Main } from './component/main/main';
import { Header } from './component/header/header';
import { Brand } from './brand/brand';
import { Model } from './car/model';

@NgModule({
  declarations: [
    App,
    Footer,
    Main,
    Header,
    Brand,
    Model
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners()
  ],
  bootstrap: [App]
})
export class AppModule { }
