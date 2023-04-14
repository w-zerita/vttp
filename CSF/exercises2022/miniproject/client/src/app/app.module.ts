import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http'

import { AppComponent } from './app.component';
import { AddContactComponent } from './components/add-contact/add-contact.component';
import { ListContactsComponent } from './components/list-contacts/list-contacts.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AddressBookService } from './services/addressbook.service';

@NgModule({
  declarations: [
    AppComponent,
    AddContactComponent,
    ListContactsComponent
  ],
  imports: [
    BrowserModule, HttpClientModule,
    FormsModule, ReactiveFormsModule
  ],
  providers: [ AddressBookService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
