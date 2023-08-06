import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Sentence} from "../model/sentence";
import {RandomDTO} from "../model/randomDTO";
import {AddToFav} from "../model/addToFav";

@Injectable({
  providedIn: 'root'
})
export class SentenceService {

  private baseURL = 'http://localhost:8080/api/v1/';
  private baseURLOpen = 'http://localhost:8080/api/v1/open/';
  private baseURLSecured = 'http://localhost:8080/api/v1/secured/';
  token: string = '';

  constructor(private httpClient: HttpClient) {
  }

  getSentenceSimpleList(token: string): Observable<Sentence[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.get<Sentence[]>(`${this.baseURLOpen}sentences`,httpOptions)
  }

  getSentenceSimpleSearch(text: string): Observable<Sentence[]> {
    return this.httpClient.get<Sentence[]>(`${this.baseURLOpen}search?textEn=${text}`)
  }

  getSentenceRandom(): Observable<RandomDTO>{
    return this.httpClient.get<RandomDTO>(`${this.baseURLOpen}random`);
  }

  getSentenceRandomForUser(email: string, token: string): Observable<RandomDTO>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.get<RandomDTO>(`${this.baseURLSecured}randomForUser?email=${email}`, httpOptions);
  }

  createSentenceSimple(sentence: Sentence): Observable<Object> {
    return this.httpClient.post(`${this.baseURLOpen}`, sentence);
  }

  getSentenceById(id: number): Observable<Sentence> {
    return this.httpClient.get<Sentence>(`${this.baseURLOpen}${id}`)
  }

  getSentenceSimpleById(id: number): Observable<Sentence> {
    return this.httpClient.get<Sentence>(`${this.baseURLOpen}simple/${id}`)
  }

  updateSentence(id: number, sentence: Sentence): Observable<Object> {
    return this.httpClient.put(`${this.baseURLOpen}${id}`, sentence);
  }

  addToFav(addToFavByUserDto:AddToFav ): Observable<AddToFav> {
    return this.httpClient.patch(`${this.baseURLOpen}add-fav`, addToFavByUserDto);
  }

  deleteSentence(id: number): Observable<Sentence> {
    return this.httpClient.delete<Sentence>(`${this.baseURLOpen}${id}`)
  }

  detailSentence(id: number): Observable<Sentence> {
    return this.httpClient.get<Sentence>(`${this.baseURLOpen}simple/${id}`)
  }

  getSecureContent(): Observable<Object> {
    this.token = localStorage.getItem('token') as string;
    let token = this.token
    console.log(token)
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.get<Object>(`${this.baseURL}demo-controller`, httpOptions);
  }
}
