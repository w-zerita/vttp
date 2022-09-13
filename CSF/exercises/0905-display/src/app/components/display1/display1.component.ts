import { Component, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-display1',
  templateUrl: './display1.component.html',
  styleUrls: ['./display1.component.css']
})
export class Display1Component implements OnInit {

  // set default value for properties to accessed by the HTML template

  // annotate class member with @Input() to allow binding of attributes by other components
  @Input()
  image = '/assets/dov0.gif'

  @Input()
  caption = 'tired bear'

  // annotate class member with @Output() to allow binding of events by other components
  @Output() // declare event to be fired by the component
  onClick = new Subject<string>()

  styleClass = 'thumbnail-0'
  fontSize = '1em'
  addToCart = true

  private count = 0

  constructor() { }

  ngOnInit(): void {
  }

  // methods/functions that binds to the HTML event
  enter() {
    console.log('cursor is in the image');
    this.fontSize = '2em'
    this.addToCart = false
  }

  exit() {
    console.log('cursor out of the image');
    this.fontSize = '1em'
    this.addToCart = true
  }

  process() {
    // send notification out
    console.log('button clicked');
    this.onClick.next(this.image) // fire the event
  }

}
