import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Todo } from 'src/app/models';
import { TodoService } from 'src/app/services/todo.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit, OnDestroy {

  todos: Todo[] = []

  // naming of subscription should end with '$'
  sub$!: Subscription

  constructor(private todoSvc: TodoService) { }

  ngOnInit(): void {
    // do subscription in service
    // this.sub$ = this.todoSvc.onNewData.subscribe(this.processData)
    this.sub$ = this.todoSvc.onNewData.subscribe(
      data => {
        console.log(">>> in sub: ", data)
        this.todos = data
      }
    )
  }

  // with multiple views, when view changes, component is destroyed
  // unsubscribe when component is destroyed to prevent memory leakage
  ngOnDestroy(): void {
      this.sub$.unsubscribe()
  }

  processData(data: Todo[]) {
    this.todos = data
  }

}
