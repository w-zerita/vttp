import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, Subject, tap } from "rxjs";
import { Todo } from "../models";

// use @Injectable({providedIn: 'root'}) to add service to root module
@Injectable()
export class TodoService {

    // not a UI, no event binding -> do not use @Output() to send event
    onNewData = new Subject<Todo[]>()

    constructor(private http: HttpClient) {}

    // return type: Promise<Todo[]>
    getTodo(userId: number): Promise<Todo[]> { // query parameter is string, but userId is logically a number
        const params = new HttpParams().set('userId', userId)
        return firstValueFrom(
            this.http.get<Todo[]>('https://jsonplaceholder.typicode.com/todos', { params })
                .pipe(
                    tap(data => {
                        this.onNewData.next(data) // data is unchanged
                    })
                )
            /*
            .then(data => {
                this.onNewData.next(data)
                return data
            })
            */
        )
    }
}