import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router'
import { HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookService } from './services/book.service';
import { BookDetailsComponent } from './components/book-details/book-details.component';

const appRoutes: Routes = [
  { path: '', component: BookListComponent },
  { path: 'books/:bookId', component: BookDetailsComponent },
  { path: '**', redirectTo:'/', pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent, BookDetailsComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    RouterModule.forRoot(appRoutes, { useHash: true })
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
