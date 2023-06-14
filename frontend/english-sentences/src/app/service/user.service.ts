import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {Injectable} from "@angular/core";
import {UserSigninDTO} from "../model/userSigninDTO";
import {UserLoginDTO} from "../model/userLoginDTO";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = 'http://localhost:8080/api/v1/auth/';

  constructor(private httpClient: HttpClient) {
  }

  createUser(user: UserSigninDTO): Observable<Object> {
    console.log(`${this.baseURL}register`)
    return this.httpClient.post(`${this.baseURL}register`, user)
  }

  authUser(user: UserLoginDTO): Observable<Object> {
    console.log(`${this.baseURL}authenticate`);
    return this.httpClient.post(`${this.baseURL}authenticate`, user).pipe(
      tap((response: any) => {
        const token = response.token; // Assuming the token is returned in the response as "token"
        // Store the token in your desired storage mechanism (e.g., local storage, state management, etc.)
        // For example, if you're using local storage:

        localStorage.setItem('token', token);
        console.log(token);
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
