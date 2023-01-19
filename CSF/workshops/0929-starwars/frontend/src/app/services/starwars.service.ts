import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { firstValueFrom } from "rxjs";
import { Category } from "../models";

const BASE_URL = "http https://swapi.dev/api/"

@Injectable()
export class StarWarsService {

    constructor(private http: HttpClient) {}

    getCategories(): Promise<Category[]> {
        return firstValueFrom(
            this.http.get<any>(BASE_URL)
        )
    }
}