import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Comparison } from 'src/app/models';

@Component({
  selector: 'app-tabular-data',
  templateUrl: './tabular-data.component.html',
  styleUrls: ['./tabular-data.component.css']
})
export class TabularDataComponent implements OnInit {
  allComparison: Comparison[] = []
  errorComparison: Comparison[] = []
  comparison: Comparison[] = [
    { topic: 'topic', test: 'test1', actual: 'actual', expectation: 'expectation', pass: false },
    { topic: 'topic', test: 'test2', actual: 'actual', expectation: 'expectation', pass: false },
    { topic: 'topic', test: 'test3', actual: 'actual', expectation: 'actual', pass: false },
    { topic: 'topic', test: 'test4', actual: 'actual', expectation: 'actual', pass: false },
    { topic: 'topic', test: 'test5', actual: 'actual', expectation: 'expectation', pass: false },
    { topic: 'topic', test: 'test6', actual: 'actual', expectation: 'actual', pass: false },
    { topic: 'topic', test: 'test7', actual: 'actual', expectation: 'expectation', pass: false },
    { topic: 'topic', test: 'test8', actual: 'actual', expectation: 'actual', pass: false },
    { topic: 'topic', test: 'test9', actual: 'actual', expectation: 'expectation', pass: false },
    { topic: 'topic', test: 'test10', actual: 'actual', expectation: 'expectation', pass: false },
  ]

  form!: FormGroup
  filter: string = ''

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.sortComparisons()
    this.form = this.createForm()
  }

  selectFilter(value: string) {
    this.filter = value
    console.log('filter: ', this.filter);
  }

  sortComparisons() {
    for(let c of this.comparison) {
      if (c.actual != c.expectation) {
        this.errorComparison.push(c)
      } else {
        c.pass = true
      }
      this.allComparison.push(c)
    }
    console.log('all comparison: ', this.allComparison);
    console.log('error comparison: ', this.errorComparison);
  }

  private createForm(): FormGroup {
    return this.fb.group({
      filter: this.fb.control<string>('all', [Validators.required])
    })
  }
}
