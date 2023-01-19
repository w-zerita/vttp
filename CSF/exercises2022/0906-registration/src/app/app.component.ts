import { Component } from '@angular/core';
import { Registration } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '0906-registration';

  names = ['fred', 'wilma', 'betty', 'barney', 'bambam', 'pebbles']

  betty: Registration = {
    name: "betty",
    email: "betty@gmail.com",
    gender: 'f',
    newsletter: false
  }

  newRegistration(reg: Registration) {
    console.log("new registration: ", reg)
  }
}
