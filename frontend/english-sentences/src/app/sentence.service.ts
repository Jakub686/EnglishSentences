import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sentence } from "./sentence";

@Injectable({
  providedIn: 'root'
})
export class SentenceService {

  private baseURL = 'http://localhost:8080/';
  constructor(private httpClient: HttpClient) { }

  getSentenceSimpleList(): Observable<Sentence[]>{
    return this.httpClient.get<Sentence[]>(`${this.baseURL}`)
  }
}
