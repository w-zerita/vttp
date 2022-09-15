import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Registration } from './models';
import { RegistrationService } from './services/registration.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'client';

  constructor(private regSvc: RegistrationService) {}

  processNewRegistration(newRegistration: Registration) {
    console.log(">>> new registration: ", newRegistration)
    this.regSvc.newRegistration(newRegistration)
      .then(result => {
        console.log(">>> result: ", result)
        alert(`Your registration ID is ${result.message}`)
      }).catch((error: HttpErrorResponse) => {
        console.error(">>> error ", error)
        alert(`Error: message = ${error.message}, data = ${error.error}`)
      })
  }
}
