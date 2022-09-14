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
  onDeleteItem = new Subject<number>()

  @Input()
  set cart(c: Item[]) {
    this._cart = c
    this.calculateTotal()
  }

  _cart: Item[] = []
  total = 0.0

  constructor() { }

  ngOnInit(): void {
  }

  deleteItem(idx: number) {
    console.log(">>> index: ", idx)
    this.onDeleteItem.next(idx)
  }

  private calculateTotal() {
    console.log(">>> calculate total")
    this.total = 0
    for (let i of this._cart)
      this.total += i.unitPrice * i.quantity
  }

}
