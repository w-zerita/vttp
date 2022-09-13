import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-display',
  templateUrl: './display.component.html',
  styleUrls: ['./display.component.css']
})
export class DisplayComponent implements OnInit {
  // set default value for properties to accessed by the HTML template
  image = '/assets/dov0.gif'
  styleClass = 'thumbnail-0'
  fontSize = '1em'
  caption = 'dov0.gif'
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
    this.count += 1
    this.count %= 3
    this.image = `/assets/dov${this.count}.gif`
    this.caption = `dov${this.count}.gif`
  }
}
