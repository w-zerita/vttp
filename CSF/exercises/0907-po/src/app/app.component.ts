import { Component } from '@angular/core';
import { Order, OrderDB } from './models';
import { v4 } from 'uuid';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '0907-po';

  order!: Order
  ordersDB: OrderDB = {}

  // install uuid: sudo npm i --save-dev @types/uuid 
  processNewOrder(newOrder: Order) {
    let orderId = !newOrder.orderId? v4().substring(0, 8): newOrder.orderId
    newOrder.orderId = orderId
    this.ordersDB = { ...this.ordersDB, [orderId]: newOrder }
  }

  editOrder(key: string) {
    this.order = this.ordersDB[key]
  }
}
