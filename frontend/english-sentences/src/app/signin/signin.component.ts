import {Component} from '@angular/core';
import {Sentence} from "../model/sentence";
import {UserLoginDTO} from "../model/userLoginDTO";
import {SentenceService} from "../service/sentence.service";
import {Router} from "@angular/router";
import {userSigninDTO} from "../model/userSigninDTO";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent {
  sentence: Sentence = {id: 0, textEn: "", translationToPl: [{id: 0, textPl: ""}, {id: 1, textPl: ""}]};
  user: userSigninDTO = {id: 0, firstname: "", lastname: "", email: "", password: ""};

  constructor(private sentenceService: SentenceService, private router: Router) {
  }

  onSubmit() {
    this.saveSentence();
    this.goToSentenceList();
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
