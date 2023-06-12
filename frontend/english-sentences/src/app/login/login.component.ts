import {Component} from '@angular/core';
import {Sentence} from "../model/sentence";
import {SentenceService} from "../service/sentence.service";
import {Router} from "@angular/router";
import {UserLoginDTO} from "../model/userLoginDTO";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  sentence: Sentence = {id: 0, textEn: "", translationToPl: [{id: 0, textPl: ""}, {id: 1, textPl: ""}]};
  user: UserLoginDTO = {email: '', password: ''}

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
