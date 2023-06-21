import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {Injectable} from "@angular/core";
import {UserSignIn} from "../model/userSignIn";
import {UserLogIn} from "../model/userLogIn";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = 'http://localhost:8080/api/v1/';

  constructor(private httpClient: HttpClient) {
  }
  createUser(user: UserSignIn): Observable<Object> {
    console.log(`${this.baseURL}register`)
    return this.httpClient.post(`${this.baseURL}auth/register`, user)
  }

  authUser(user: UserSignIn): Observable<Object> {
    console.log(`${this.baseURL}authenticate`);
    return this.httpClient.post(`${this.baseURL}auth/authenticate`, user).pipe(
      tap((response: any) => {
        const token = response.token; // Assuming the token is returned in the response as "token"
        // Store the token in your desired storage mechanism (e.g., local storage, state management, etc.)
        // For example, if you're using local storage:
        localStorage.setItem('token', token);
      })
    );
  }

  getSecureContent(token: string): Observable<Object> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Bearer ${token}`
      })
    };
    return this.httpClient.get<Object>(`${this.baseURL}demo-controller`, httpOptions);
  }

}




