import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { BookDetails } from 'src/app/models';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  bookDetails!: BookDetails
  bookId!: string

  constructor(private bookSvc: BookService, private activatedRoute: ActivatedRoute, private title: Title) { }

  ngOnInit(): void {
    console.log('+++ ngOnInit')
    this.bookDetails = { // prevent error due to asynchronous calls
      bookId: '', 
      title: '', 
      authors: '',
      description: '',
      rating: 0,
      genres: '',
      imageUrl: ''
    }
    this.bookId = this.activatedRoute.snapshot.params['bookId']
    this.title.setTitle(`Book: ${this.bookId}`)
    this.getBookDetails(this.bookId)
  }

  getBookDetails(bookId: string) {
    this.bookSvc.getBookDetails(bookId)
      .then(result => {
        console.log('>>> books details: ', result)
        this.bookDetails = result
      }).catch(error => 
        console.error('>>> error: ', error)
      )
  }

}
