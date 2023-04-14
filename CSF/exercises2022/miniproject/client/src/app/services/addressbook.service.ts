import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, map } from "rxjs";
import { Contact, Response } from "../models";


const ADD_CONTACT_URL = 'https://wz-csf-miniproject.herokuapp.com/api/addressbook/add'
const LIST_CONTACTS_URL = 'https://wz-csf-miniproject.herokuapp.com/api/addressbook/list'
const DELETE_CONTACT_URL = 'https://wz-csf-miniproject.herokuapp.com/api/addressbook/delete'

@Injectable()
export class AddressBookService {

    constructor(private http: HttpClient) {}

    newContact(contact: Contact) {
        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Accept', 'application/json')

        return firstValueFrom(
            this.http.post<Response>(ADD_CONTACT_URL, contact, { headers })
        )
    }
    
    listContacts() {
        return firstValueFrom(
            this.http.get<any>(LIST_CONTACTS_URL)
                .pipe(
                    map(result => {
                        const contacts = result.contacts
                        return contacts.map((v:any) => v as Contact)
                    })
                )
        )
    }

    deleteContact(contact: Contact) {
        const headers = new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Accept', 'application/json')

        return firstValueFrom(
            this.http.post<Response>(DELETE_CONTACT_URL, contact, { headers })
        )
    }
}