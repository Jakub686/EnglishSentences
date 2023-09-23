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

  getSentenceList(email: string, token: string): Observable<RandomDTO[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.get<RandomDTO[]>(`${this.baseURLSecured}sentences-logged?email=${email}`, httpOptions)
  }

  search(text: string, token: string): Observable<RandomDTO[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.get<RandomDTO[]>(`${this.baseURLSecured}search?textEn=${text}`, httpOptions)
  }

  getSentenceRandom(): Observable<RandomDTO> {
    return this.httpClient.get<RandomDTO>(`${this.baseURLOpen}random`);
  }

  getSentenceRandomForUser(email: string, fav: boolean, token: string): Observable<RandomDTO> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    console.log('random for user');
    return this.httpClient.get<RandomDTO>(`${this.baseURLSecured}random-for-user?email=${email}&fav=${fav}`, httpOptions);
  }

  createSentenceSimple(sentence: Sentence, token: string): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.post(`${this.baseURLSecured}`, sentence, httpOptions);
  }

  getSentenceById(id: number, token: string): Observable<Sentence> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.get<Sentence>(`${this.baseURLSecured}${id}`, httpOptions)
  }

  updateSentence(id: number, sentence: Sentence, token: string): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.put(`${this.baseURLSecured}${id}`, sentence, httpOptions);
  }

  addToFav(addToFavByUserDto: AddToFav, token: string): Observable<AddToFav> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.patch(`${this.baseURLSecured}add-fav`, addToFavByUserDto, httpOptions);
  }

  deleteSentence(id: number, token: string): Observable<Sentence> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.delete<Sentence>(`${this.baseURLSecured}${id}`, httpOptions)
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
