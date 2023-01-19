import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models';
import { StarWarsService } from 'src/app/services/starwars.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[] = []

  constructor(private starWarsSvc: StarWarsService) { }

  ngOnInit(): void {
    this.getCategories()
  }

  getCategories() {
    this.starWarsSvc.getCategories()
      .then(result => {
        console.log(`>>> result: ${result}`)
        this.categories = result
      })
      .catch(err => {
        console.error(`>>> error: ${err}`)
        alert(`>>> error ${JSON.stringify(err)}`)
      })
  }
}
