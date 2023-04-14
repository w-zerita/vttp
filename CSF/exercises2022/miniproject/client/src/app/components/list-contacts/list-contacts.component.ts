import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Contact } from 'src/app/models';
import { AddressBookService } from 'src/app/services/addressbook.service';

@Component({
  selector: 'app-list-contacts',
  templateUrl: './list-contacts.component.html',
  styleUrls: ['./list-contacts.component.css']
})
export class ListContactsComponent implements OnInit {

  @Output()
  onDeleteContact = new Subject<Contact>()

  contacts: Contact[] = []

  constructor(private addressBookSvc: AddressBookService) { }

  ngOnInit(): void {
    this.listAllContacts()
  }

  deleteContact(c: Contact) {
    if (confirm('Proceed to delete contact?')) {
      this.addressBookSvc.deleteContact(c)
      .then(result => {
        console.log(">>> result: ", result)
        alert(`${result.message}`)
        this.onDeleteContact.next(c)
        this.listAllContacts()
      }).catch((error: HttpErrorResponse) => {
        console.error(">>> error ", error)
        alert(`Error: message = ${error.message}, data = ${error.error}`)
      })
    }
    else {
      this.listAllContacts()
    }
  }

  listAllContacts() {
    this.addressBookSvc.listContacts()
      .then(data => {
        this.contacts = data
        console.log(">>> contacts: ", data)
      }).catch((error: HttpErrorResponse) => {
        console.error(">>> error ", error)
        alert(`Error: message = ${error.message}, data = ${error.error}`)
      })
  }
}
