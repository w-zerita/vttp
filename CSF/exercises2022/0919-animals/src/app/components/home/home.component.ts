import { Component, OnDestroy, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  constructor() { }

  ngOnInit(): void {
    console.log('+++ ngOnInit')
    // sub$ = this.onEvent.subscribe()
  }

  ngOnDestroy(): void {
    console.log('+++ ngOnDestroy')
    // sub$.unsubscribe() // clean up subscription to prevent garbage collection
  }

}
