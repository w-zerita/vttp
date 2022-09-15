import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http'

import { AppComponent } from './app.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { RegistrationService } from './services/registration.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    FormsModule, ReactiveFormsModule
  ],
  providers: [ RegistrationService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
