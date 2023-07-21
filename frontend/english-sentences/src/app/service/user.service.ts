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
  token: string;
  login: string | null = '';

  constructor(private httpClient: HttpClient) {
    this.token = localStorage.getItem('token') as string;
  }

  createUser(user: UserSignIn): Observable<Object> {
    console.log(`${this.baseURL}register`)
    return this.httpClient.post(`${this.baseURL}auth/register`, user);
  }

  authUser(user: UserSignIn): Observable<Object> {
    let login = user.email;

    return this.httpClient.post(`${this.baseURL}auth/authenticate`, user).pipe(
      tap((response: any) => {

        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);

        if (typeof login === "string") {
          localStorage.setItem('login', login);
          window.dispatchEvent(new StorageEvent('storage', {
            key: 'login',
            newValue: login
          }));
        }
      })
    );
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

  getLogin(): string | null {
    this.login = localStorage.getItem('login');
    return this.login;
  }

  logout(): string | null {
    localStorage.setItem('token', '');
    localStorage.setItem('login', '');
    window.dispatchEvent(new StorageEvent('storage', {
      key: 'login',
      newValue: ''
    }));
    return localStorage.getItem('login');
  }
}
