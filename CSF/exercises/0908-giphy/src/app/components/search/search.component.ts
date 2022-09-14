import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GiphyService } from 'src/app/giphy.service';
import { SearchCriteria } from 'src/app/models';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  results: number[] = []
  searchForm!: FormGroup

  constructor(private fb: FormBuilder, private giphySvc: GiphyService) { }

  ngOnInit(): void {
    this.results = Array(6).fill(0).map((x, i)=>(i + 1) * 5);
    this.searchForm = this.createForm()
  }

  performSearch() {
    const criteria: SearchCriteria = this.searchForm.value as SearchCriteria
    console.log(`>>> search criteria: ${criteria}`)
    this.giphySvc.search(criteria)
      .then(result => {
        console.log(`>>> result: ${result}`)
        // only save apiKey if call is successful
        this.saveAPIKey(criteria.apiKey)
        this.searchForm = this.createForm()
        this.giphySvc.onNewResult.next(result)
      })
      .catch(err => {
        console.error(`>>> error: ${err}`)
        alert(`>>> error ${JSON.stringify(err)}`)
      })
  }

  private createForm(): FormGroup {
    return this.fb.group({
      apiKey: this.fb.control<string>(this.getAPIKey(), [Validators.required]),
      query: this.fb.control<string>('', [Validators.required]),
      results: this.fb.control<number>(10, [Validators.min(5)]),
      rating: this.fb.control<string>('g')
    })
  }

  private getAPIKey(): string {
    let key = localStorage.getItem('apiKey')
    if (!key)
      return ''
    return key
  }

  private saveAPIKey(key: string) {
    localStorage.setItem('apiKey', key)
  }
}
