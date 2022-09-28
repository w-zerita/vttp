import { Component, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { Item } from 'src/app/models';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

  @Output()
  onAddItem = new Subject<Item>()

  item!: Item
  items: Item[] = []

  constructor() {}

  ngOnInit(): void {
    this.populateInventory()
  }

  addItem(idx: number) {
    console.log(`>>> add item ${idx + 1}`)
    console.log('>>> item: ', this.items[idx])
    this.items[idx].quantity++
    console.log('>>> quantity: ', this.items[idx].quantity)
    this.onAddItem.next(this.items[idx])
  }

  populateInventory() {
    for(let i = 1; i < 5; i++) {
      this.item = {
        image: `/assets/numbers/number${i}.jpg`,
        description: `Item ${i}`,
        quantity: 0
      }
      this.items.push(this.item)
      // this.items = [ ...this.items, this.item ]
    }
    console.log('>>> items: ', this.items)
  }
}
