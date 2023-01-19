import { Component } from '@angular/core';
import { Item } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '0906-shoppingCart';

  // initialise an empty cart
  cart: Item[] = []

  // access item added from item component
  newItem(newItem: Item) {
    console.log(">>> new item: ", newItem)
    // use the spread operator '...' to return all elements of an array
    // in this case, create a new array by adding new elements to an existing array
    this.cart = [ ...this.cart, newItem ]
  }

  deleteItem(idx: number) {
    const tmp: Item[] = [ ...this.cart ]
    // splice changes contents of an array by removing or replacing existing elements 
    // and/or adding new elements in place
    // idx is the index at which to start changing the array, 1 is the number of elements to remove from the start
    tmp.splice(idx, 1) // (start, deleteCount)
    this.cart = tmp
  }
}
