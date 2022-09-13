import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { DisplayComponent } from './components/display/display.component';
import { Display1Component } from './components/display1/display1.component';

@NgModule({
  declarations: [
    AppComponent,
    DisplayComponent,
    Display1Component
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
