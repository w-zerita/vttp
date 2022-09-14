import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { Item } from 'src/app/models';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  @Output()
  onNewItem = new Subject<Item>()

  // item!: Item

  form!: FormGroup
  itemQty: number[] = []

  constructor(private fb: FormBuilder) { 
    console.log("fb: ", fb)
    this.fb = fb
  }

  ngOnInit(): void {
    this.itemQty = Array(10).fill(0).map((x, i)=> i + 1);
    this.form = this.createForm()
  }

  private createForm() {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      unitPrice: this.fb.control<number>(.1, [Validators.required, Validators.min(.1)]),
      quantity: this.fb.control<number>(1, [Validators.required, Validators.min(1)])
    })
  }

  processForm() {
    const item: Item = this.form.value as Item
    console.log(">>> item: ", item)
    this.form = this.createForm()
    this.onNewItem.next(item)
  }

}
