import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Contact } from './models';
import { AddressBookService } from './services/addressbook.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';

  showAddContact = true

  addContact(): boolean {
    return this.showAddContact = true
  }

  listContacts(): boolean {
    return this.showAddContact = false
  }
}
