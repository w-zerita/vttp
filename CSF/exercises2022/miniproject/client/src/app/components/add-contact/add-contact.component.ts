import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Contact } from 'src/app/models';
import { AddressBookService } from 'src/app/services/addressbook.service';

@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
})
export class AddContactComponent implements OnInit {

  @Output()
  onNewContact = new Subject<Contact>()

  form!: FormGroup

  constructor(private fb: FormBuilder, private addressBookSvc: AddressBookService) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  processForm() {
    const contact = this.form.value as Contact
    console.log(">>> contact: ", contact)
    this.addressBookSvc.newContact(contact)
      .then(result => {
        console.log(">>> result: ", result)
        alert(`New contact has been added: ${result.message}`)
        this.form = this.createForm()
        this.onNewContact.next(contact)
      }).catch((error: HttpErrorResponse) => {
        console.error(">>> error ", error)
        alert(`Error: message = ${error.message}, data = ${error.error}`)
      })
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      mobile: this.fb.control<string>('', [Validators.required])
    })
  }

}
