import {Component, OnInit} from '@angular/core';
import {Sentence} from "../model/sentence";
import {Router} from "@angular/router";
import {SentenceService} from "../service/sentence.service";
import {TranslationToPl} from "../model/translationToPl";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-create-sentence',
  templateUrl: './create-sentence.component.html',
  styleUrls: ['./create-sentence.component.css']
})
export class CreateSentenceComponent {

  sentence: Sentence = {id: 0, textEn: "", textPl: ""};
  token: string;
  email: string;
  role: string;

  constructor(private sentenceService: SentenceService, private userService: UserService, private router: Router) {
    this.token = localStorage.getItem('token') as string;
    this.role = localStorage.getItem('role') as string;
    this.email = localStorage.getItem('email') as string;
  }

  onSubmit() {
    console.log(this.sentence);
    console.log(this.sentence.textEn);
    console.log(this.sentence.textPl);
    this.saveSentence();
    this.goToSentenceList();
  }

  saveSentence() {
    this.sentenceService.createSentenceSimple(this.sentence, this.token).subscribe(data => {
        console.log(data);
      },
      error => console.log(error));
  }


  goToSentenceList() {
    this.router.navigate(['/sentences'])
  }
}


