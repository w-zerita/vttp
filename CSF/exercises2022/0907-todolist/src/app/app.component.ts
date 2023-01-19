import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { TodoComponent } from './components/todo/todo.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {
  title = '0907-todolist';

  // Todo Component
  @ViewChild(TodoComponent) // ask for object from child component
  todoComp!: TodoComponent

  @ViewChild('todo') // use @ViewChild('todo') to reference template
  todoElemRef!: ElementRef

  @ViewChildren(TodoComponent)
  todoComps!: QueryList<TodoComponent>

  ngOnInit(): void {
    console.log('>>> onInit todoComp: ', this.todoComp)
  }

  ngAfterViewInit(): void {
    console.log('>>> ngAfterViewInit todoComp: ', this.todoComp)
    console.log('>>> ngAfterViewInit todoElemRef: ', this.todoElemRef.nativeElement)
    console.log('>>> ngAfterViewInit todoComps: ', this.todoComps.length)
  }

  saveTodo() {
    const todo = this.todoComp.getValues()
    console.log(">>> saveTodo: ", todo)
  }

  // List Component
  value: number = 3
  numList: number[] = []

  randomise() {
    this.value = Math.floor(Math.random() * 100)
    // create a new array
    const tmp: number[] = [ ...this.numList, this.value ]
    // assign the new array to the binding (force reference to change)
    this.numList = tmp
  }
}
