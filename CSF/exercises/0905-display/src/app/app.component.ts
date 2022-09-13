import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '0905-display';

  image = '/assets/dov1.gif'
  count = 0

  imageClicked($event: string) {
    console.info(">>>>> app.component: image clicked", $event)
    this.image = $event
    this.count++
  }
}
