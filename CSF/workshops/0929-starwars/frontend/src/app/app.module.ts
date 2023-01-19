import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CategoryComponent } from './components/category/category.component';
import { ItemsComponent } from './components/items/items.component';
import { DetailsComponent } from './components/details/details.component';
import { MaterialModule } from './material.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'


const appRoutes: Routes = [
  { path: '', component: CategoryComponent },
  { path: 'items', component: ItemsComponent },
  { path: 'details', component: DetailsComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent, ItemsComponent,
    DetailsComponent
  ],
  imports: [
    BrowserModule, MaterialModule,
    HttpClientModule, FormsModule,
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
