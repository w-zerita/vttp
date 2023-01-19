import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { WebcamModule } from 'ngx-webcam';
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { WebcamComponent } from './components/webcam/webcam.component';
import { UploadComponent } from './components/upload/upload.component';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { WebcamService } from './services/webcam.service';

const appPath: Routes = [
  { path: '', component: WebcamComponent },
  { path: 'upload', component: UploadComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    WebcamComponent,
    UploadComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    ReactiveFormsModule, 
    RouterModule.forRoot(appPath, { useHash: true }),
    WebcamModule
  ],
  providers: [ WebcamService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
