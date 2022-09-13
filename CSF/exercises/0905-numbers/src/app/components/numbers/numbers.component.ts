import { Component, Input, OnInit, Output } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-numbers',
  templateUrl: './numbers.component.html',
  styleUrls: ['./numbers.component.css']
})
export class NumbersComponent implements OnInit {
  
  // set random number between 0 and 30 (inclusive) from the root component
  @Input()
  set currNum(n: number) {
    console.log(">>> set: ", n)
    this._currNum = n
    this.updateImg(n)
  }

  get currNum(): number {
    return this._currNum
  }

  private _currNum = 0
  // num!: number

  @Output() // define event called onNumberClicked
  onNumberClicked = new Subject<number>()

  image!: string

  constructor() { }

  ngOnInit(): void {
    this.updateImg(this._currNum)
  }

  numberClicked() {
    console.log(`>>> currNum: ${this._currNum}`)
    this.onNumberClicked.next(this._currNum) // fire event with this._currNum
  }

  next() {
    this._currNum++
    if (this._currNum % 31 == 0)
      this._currNum = 0
    this.updateImg(this._currNum)
  }

  previous() {
    this._currNum--
    if (this._currNum < 0)
      this._currNum = 30
    this.updateImg(this._currNum)
  }

  private updateImg(n: number) {
    console.log(">>> number: ", n)
    this.image = `/assets/numbers/number${n}.jpg`
  }

}
