import {Component} from '@angular/core';
import {Sentence} from "../model/sentence";
import {UserLoginDTO} from "../model/userLoginDTO";
import {SentenceService} from "../service/sentence.service";
import {Router} from "@angular/router";
import {UserSigninDTO} from "../model/userSigninDTO";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  sentence: Sentence = {id: 0, textEn: "", translationToPl: [{id: 0, textPl: ""}, {id: 1, textPl: ""}]};
  userSignIn: UserSigninDTO = {id: 0, firstname: "", lastname: "", email: "", password: ""};
  token: string = '';

  constructor(private sentenceService: SentenceService, private userService: UserService , private router: Router) {
  }

  saveUser() {
    this.userService.createUser(this.userSignIn).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
  }

  goToSentenceList() {
    this.router.navigate(['/sentences'])
  }
}
