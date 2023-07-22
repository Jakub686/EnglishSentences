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
  sentence: Sentence = {id: 0, textEn: "", translationToPl: [{id: 0, textPl: ""}, {id: 1, textPl: ""}]};
  userLogIn: UserLogIn = {email: '', password: '', role: ''};
  token: string = '';

  constructor(private sentenceService: SentenceService, private userService: UserService, private router: Router) {
  }

  onSubmit() {
    this.saveSentence();
    this.goToSentenceList();
  }

  authUser() {

    this.userService.authUser(this.userLogIn).subscribe(
      (data: any) => {
        console.log(data);
        this.token = data.token;

      },
      error => console.log(error)
    );
    console.log(this.token);
    console.log('role ' + localStorage.getItem('role'));


  }

  saveSentence() {
    this.sentenceService.createSentenceSimple(this.sentence).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
  }

  goToSentenceList() {
    this.router.navigate(['/sentences'])
  }
}
