import {Component} from '@angular/core';
import {Sentence} from "../model/sentence";
import {UserLogIn} from "../model/userLogIn";
import {SentenceService} from "../service/sentence.service";
import {Router} from "@angular/router";
import {UserSignIn} from "../model/userSignIn";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {

  userSignIn: UserSignIn = {id: 0, firstname: "", lastname: "", email: "", password: ""};
  userLogIn: UserLogIn = {email: "", password: ""};
  token: string = '';
  login = '';

  constructor(private userService: UserService, private sentenceService: SentenceService, private router: Router) {
    this.login = localStorage.getItem('login') as string;
  }
  ngOnInit() {
    console.log('login' + this.login);
  }

  authUser() {
    console.log(this.userLogIn.email)
    console.log(this.userLogIn.password)
    this.userService.authUser(this.userLogIn).subscribe(
      (data: any) => {
        console.log(data);
        this.token = data.token;
      },
      error => console.log(error)
    );
  }

  saveUser() {
    this.userService.createUser(this.userSignIn).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
  }

  getContent() {
    this.sentenceService.getSecureContent().subscribe(
      data => {
        console.log(data); // The response is a string, so you can directly access it
      },
      error => console.log(error)
    );
  }

}
