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

  getSentenceList(email: string,token: string): Observable<RandomDTO[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.get<RandomDTO[]>(`${this.baseURLSecured}sentences-logged?email=${email}`,httpOptions)
  }

  getSentenceSimpleSearch(text: string): Observable<Sentence[]> {
    return this.httpClient.get<Sentence[]>(`${this.baseURLOpen}search?textEn=${text}`)
  }

  getSentenceRandom(): Observable<RandomDTO>{
    return this.httpClient.get<RandomDTO>(`${this.baseURLOpen}random`);
  }

  getSentenceRandomForUser(email: string, fav: boolean, token: string): Observable<RandomDTO>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
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
    return this.httpClient.get<Sentence>(`${this.baseURLSecured}${id}`,httpOptions)
  }

  // getSentenceSimpleById(id: number): Observable<Sentence> {
  //   return this.httpClient.get<Sentence>(`${this.baseURLOpen}simple/${id}`)
  // }

  updateSentence(id: number, sentence: Sentence): Observable<Object> {
    return this.httpClient.put(`${this.baseURLOpen}${id}`, sentence);
  }

  addToFav(addToFavByUserDto:AddToFav ): Observable<AddToFav> {
    return this.httpClient.patch(`${this.baseURLOpen}add-fav`, addToFavByUserDto);
  }

  deleteSentence(id: number): Observable<Sentence> {
    return this.httpClient.delete<Sentence>(`${this.baseURLOpen}${id}`)
  }

  // detailSentence(id: number): Observable<Sentence> {
  //   return this.httpClient.get<Sentence>(`${this.baseURLOpen}simple/${id}`)
  // }

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
