import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sentence} from "../sentence";

@Injectable({
  providedIn: 'root'
})
export class SentenceService {

  private baseURL = 'http://localhost:8080/api/v1/open/';

  constructor(private httpClient: HttpClient) {
  }

  getSentenceSimpleList(): Observable<Sentence[]> {
    return this.httpClient.get<Sentence[]>(`${this.baseURL}`)
  }

  getSentenceSimpleSearch(text: string): Observable<Sentence[]> {
    return this.httpClient.get<Sentence[]>(`${this.baseURL}search?textEn=${text}`)
  }

  getSentenceRandom(): Observable<Sentence>{
    return this.httpClient.get<Sentence>(`${this.baseURL}random`);
  }

  createSentenceSimple(sentence: Sentence): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, sentence);
  }

  getSentenceById(id: number): Observable<Sentence> {
    return this.httpClient.get<Sentence>(`${this.baseURL}${id}`)
  }

  getSentenceSimpleById(id: number): Observable<Sentence> {
    return this.httpClient.get<Sentence>(`${this.baseURL}simple/${id}`)
  }

  updateSentence(id: number, sentence: Sentence): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}${id}`, sentence);
  }

  deleteSentence(id: number): Observable<Sentence> {
    return this.httpClient.delete<Sentence>(`${this.baseURL}${id}`)
  }

  detailSentence(id: number): Observable<Sentence> {
    return this.httpClient.get<Sentence>(`${this.baseURL}simple/${id}`)
  }
}
