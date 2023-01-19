import { Component } from '@angular/core';
import { Todo } from './models';
import { TodoService } from './services/todo.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = '0908-deferNPromise';

  constructor(private todoSvc: TodoService) {}

  getData() {
    this.todoSvc.getTodo(Math.floor(Math.random() * 10) + 1)
      .then(this.take10)
      .then(this.processTodo)
      .catch(err => {
        console.error('errror: ', err)
      })
  }

  // constructor(private http: HttpClient) {}

  // getData() {
  //   let params: HttpParams = new HttpParams().set("userId", 4)
  //   console.log("=============== before call ===============");
  //   // take the last value from the observable and return the result as a promise
  //   lastValueFrom(
  //     // returns an Observable<Todo[]>
  //     // Note: if key name and variable name of params is the different, use { keyName: variableName }
  //     this.http.get<Todo[]>('https://jsonplaceholder.typicode.com/todos', {params})
  //       .pipe())
  //     .then(this.take10)
  //     .then(this.completedTasks)
  //     .then(this.processTodo)
  //     .catch((error: HttpErrorResponse) => {
  //       // handle the error
  //       console.error('>>> error: ', error.message)
  //     })
  //   console.log("=============== after call ===============");
  // }

  //filter
  completedTasks(todo: Todo[]) {
    return todo.filter(v => v.completed)
  }

  // return the first 10
  take10(todo: Todo[]) {
    return todo.slice(0, 10)
  }

  processTodo(todo: Todo[]) {
    console.log('>>> processing todos: ', todo);
  }
}
