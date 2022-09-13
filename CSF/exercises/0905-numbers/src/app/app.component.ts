import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = '0905-numbers';

  currNum = 0
  selectedNumbers: number[] = []
  selectedNumbersString!: string

  ngOnInit(): void {
    this.reset()
  }

  numberClicked(n: number) {
    console.log(`number selected: ${n}`)
    this.selectedNumbers.push(n)
    this.selectedNumbersString = this.selectedNumbers.join(", ")
  }

  generateRandomNum($max: number, $min: number) {
    return Math.floor(Math.random() * ($max - $min + 1) + $min)
  }

  reset() {
    this.currNum = this.generateRandomNum(30, 0)
    this.selectedNumbersString = 'No number selected yet'
  }
}
