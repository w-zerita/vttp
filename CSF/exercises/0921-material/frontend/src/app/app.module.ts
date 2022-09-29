import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './components/register/register.component';
import { RouterModule, Routes } from '@angular/router';
import { UploadComponent } from './components/upload/upload.component'
import { HttpClientModule } from '@angular/common/http';
import { UploadService } from './services/upload.service';


const appRoute: Routes = [
  { path: '', component: RegisterComponent },
  { path: 'upload', component: UploadComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    UploadComponent
  ],
  imports: [
    BrowserModule, BrowserAnimationsModule, HttpClientModule,
    MaterialModule, ReactiveFormsModule,
    RouterModule.forRoot(appRoute, { useHash: true })
  ],
  providers: [UploadService],
  bootstrap: [AppComponent]
})
export class AppModule { }
