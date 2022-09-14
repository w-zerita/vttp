import { Component, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';
import { OrderDB } from 'src/app/models';

@Component({
  selector: 'app-ledger',
  templateUrl: './ledger.component.html',
  styleUrls: ['./ledger.component.css']
})
export class LedgerComponent implements OnInit {

  @Input()
  ordersDB!: OrderDB

  @Output()
  onEdit = new Subject<string>()

  constructor() { }

  ngOnInit(): void {
  }

  edit(key: string) {
    this.onEdit.next(key)
    console.log(">>> edit order: ", key)
  }

}
