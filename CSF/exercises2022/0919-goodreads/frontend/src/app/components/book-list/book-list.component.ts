import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { BookSummary } from 'src/app/models';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  
  books: BookSummary[] = []
  current = 0
  limit = 20
  offset = 0
  startOfList!: boolean
  endOfList!: boolean
  count!: number
  pages!: number

  constructor(private bookSvc: BookService, private title: Title) { }

  ngOnInit(): void {
    this.title.setTitle('Goodreads')
    this.getBooksCount()
    this.getBooks(this.limit, this.offset)
  }

  getBooks(limit?: number, offset?:number) {
    this.bookSvc.getBooks(limit, offset)
      .then(result => {
        console.log('>>> books: ', result)
        this.books = result
        console.log('>>> limit: %d, offset: %d', limit, offset)
        this.disableBtn()
      }).catch(error => 
        console.error('>>> error: ', error)
      )
  }

  getBooksCount() {
    this.bookSvc.getBooksCount()
      .then(result => {
        this.count = result
        console.log('>>> count: ', this.count)
        this.pages = Math.floor(this.count / this.limit)
        console.log('>>> total pages: ', this.pages)
      }).catch(error => 
        console.error('>>> error: ', error)
      )
  }

  previous() {
    if (this.current > 0) {
      this.current--
      this.offset = this.current * this.limit
      this.getBooks(this.limit, this.offset)
      console.log(">>> Page: ", this.current)
    }
  }

  next() {
    this.current++
    this.offset = this.current * this.limit
    this.getBooks(this.limit, this.offset)
    console.log(">>> Page: ", this.current)
  }

  disableBtn() {
    this.startOfList = false
    this.endOfList = false
    if (this.current == 0) {
      this.startOfList = true
    }
    if (this.current == this.pages) {
      this.endOfList = true
    }
  }

}
