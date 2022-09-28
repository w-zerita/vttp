import { Component, Input, OnInit } from '@angular/core';
import { Item } from 'src/app/models';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  @Input()
  set cartItems(item: Item[]) {
    this._cartItems = item
  }

  _cartItems: Item[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
