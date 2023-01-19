import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  path = [ '', '/cat', '/dog' ]

  constructor(private router: Router) {} // inject router to control navigation from typescript

  clicked() {
    console.log('button clicked')
    const i = Math.floor(Math.random() * 3)
    this.router.navigate([ this.path[i] ])
  }
}
