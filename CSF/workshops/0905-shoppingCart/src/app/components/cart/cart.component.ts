import { Component, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Item } from 'src/app/models';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  @Output()
  onRemoveItem = new Subject<number>

  @Input()
  set cartItems(item: Item[]) {
    this._cartItems = item
  }

  _cartItems: Item[] = []

  constructor() { }

  ngOnInit(): void {
  }

  removeItem(idx: number) {
    console.log(`>>> remove item ${idx + 1}`)
    console.log('>>> item: ', this._cartItems[idx])
    this._cartItems[idx].quantity = 0
    this.onRemoveItem.next(idx)
  }

}
