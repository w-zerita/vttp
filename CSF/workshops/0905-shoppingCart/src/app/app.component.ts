import { Component } from '@angular/core';
import { Item } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '0905-shoppingCart';

  cartItems: Item[] = []

  addItem(item: Item) {
    // check if item has been added to cart
    if (!this.cartItems.includes(item)) {
      this.cartItems.push(item)
    }
    console.log('>>> add item: ', item)
  }

  removeItem(idx: number) {
    const tmp: Item[] = [ ...this.cartItems ]
    tmp.splice(idx, 1)
    this.cartItems = tmp
  }
}
