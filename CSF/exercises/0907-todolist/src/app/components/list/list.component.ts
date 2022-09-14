import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  @Input()
  set value(n: number) {
    this._value = n
    console.log(">>> setting value: ", this._value)
  }
  get value(): number {
    return this._value
  }

  @Input()
  set numList(n: number[]) {
    this._numList = n
    console.log(">>> setting numList: ", this._numList)
    this._value = this._numList.reduce((acc, v) => acc + v)
  }

  _value!: number
  _numList!: number[]

  constructor() { }

  ngOnInit(): void {
  }

}
