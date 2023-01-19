import { Component, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { LineItem, Order } from 'src/app/models';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit, OnChanges {

  @Output()
  onNewOrder = new Subject<Order>()

  @Input()
  order!: Order

  orderForm!: FormGroup
  lineItemsArray!: FormArray // update tsconfig.json > "strictTemplates": false
  _order!: Order

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.orderForm = this.createOrder(this.order)
  }

  ngOnChanges(changes: SimpleChanges): void {
      console.log(">>> changes: ", changes)
      console.log(">>> orderForm.dirty: ", this.orderForm?.dirty)
      if (this.orderForm?.dirty && !confirm(`Discard changes?`)) {
        this.order = this._order
        return
      }
      this.orderForm = this.createOrder(this.order)
      this._order = this.order
  }

  addItem() {
    this.lineItemsArray.push(this.createLineItem())
  }

  removeItem(idx: number) {
    this.lineItemsArray.removeAt(idx)
  }

  processOrder() {
    const order: Order = this.orderForm.value as Order
    // update existing order using orderId
    if (!! this.order?.orderId) { // use !! to cast variable to be a Boolean (true or false) value
      order.orderId = this.order.orderId
      // @ts-ignore
      this.order = null
      this._order = this.order
    }
    console.log(">>> order: ", order)
    this.orderForm = this.createOrder()
    this.onNewOrder.next(order)
  }

  private createOrder(order?: Order): FormGroup {
    this.lineItemsArray = this.createLineItems(order?.lineItems || [])
    return this.fb.group({
      name: this.fb.control<string>(this.order?.name, [Validators.required, Validators.minLength(3)]),
      mobile: this.fb.control(this.order?.mobile, [Validators.required]),
      lineItems: this.lineItemsArray
    })
  }

  private createLineItems(lis: LineItem[] = []): FormArray {
    return this.fb.array(lis.map(li => this.createLineItem(li)))
  }

  private createLineItem(li?: LineItem): FormGroup {
    return this.fb.group({
      item: this.fb.control<string>(li?.item || '', Validators.required),
      quantity: this.fb.control<number>(li?.quantity || 1)
    })
  }

}
