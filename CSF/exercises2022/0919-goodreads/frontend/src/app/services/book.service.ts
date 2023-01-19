import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom, map } from "rxjs";
import { BookDetails, BookSummary } from "../models";

@Injectable()
export class BookService {

    constructor(private http: HttpClient) {}

    getBooks(limit = 20, offset = 0): Promise<BookSummary[]> { // default values
        const params = new HttpParams()
            .set("limit", limit).set("offset", offset)

        return firstValueFrom(
            this.http.get<BookSummary[]>('/api/books', { params })
        )
    }

    getBookDetails(bookId: string): Promise<BookDetails> {
        return firstValueFrom(
            this.http.get<BookDetails>(`/api/books/${bookId}`)
        )
    }

    getBooksCount(): Promise<number> {
        return firstValueFrom( 
            this.http.get<any>('/api/books/count')
                .pipe(
                    map(result => {
                        const count = result.count
                        return count as number
                    })
                )
        )
    }
}