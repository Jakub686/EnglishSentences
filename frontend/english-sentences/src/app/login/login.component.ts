import {Component} from '@angular/core';
import {Sentence} from "../model/sentence";
import {SentenceService} from "../service/sentence.service";
import {Router} from "@angular/router";
import {UserLogIn} from "../model/userLogIn";
import {UserSignIn} from "../model/userSignIn";
import {Observable, tap} from "rxjs";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  sentence: Sentence = {id: 0, textEn: "", textPl: ""};
  userLogIn: UserLogIn = {email: 'root@gmail.com', password: '1234', role: ''};
  token: string = '';

  constructor(private sentenceService: SentenceService, private userService: UserService, private router: Router) {
  }


  authUser() {
    this.userService.authUser(this.userLogIn).subscribe(
      (data: any) => {
        console.log(data);
        this.token = data.token;
        setTimeout(() => {
          this.router.navigate(['/main']);
        }, 100);
      },
      error => console.log(error)
    );





  }

}
