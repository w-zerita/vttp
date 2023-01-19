import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  form!: FormGroup
  todoArrayCtrl!: FormArray

  constructor(private fb: FormBuilder) { 
    console.log(">>> fb: ", fb)
    this.fb = fb
  }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  // for root component
  getValues() {
    return this.form.value
  }

  get invalid(): boolean {
    return this.form.invalid
  }

  // reactive form
  addTodo() {
    const todo = this.fb.group({
      task: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      priority: this.fb.control<number>(1, [Validators.min(1), Validators.max(5)])
    })
    this.todoArrayCtrl.push(todo)
  }

  removeTask(idx: number) {
    this.todoArrayCtrl.removeAt(idx)
  }

  hasError(ctrlName: string) {
    return this.form.get(ctrlName)?.hasError('required')
  }

  processForm() {
    const data = this.form.value
    console.log(">>> data: ", data);
  }

  private createForm(): FormGroup {
    this.todoArrayCtrl = this.fb.array([], [Validators.min(1)])
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      email: this.fb.control<string>('', [Validators.required, Validators.email]),
      todos: this.todoArrayCtrl
    })
  }

}
