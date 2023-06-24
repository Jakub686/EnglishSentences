import {Component, OnInit} from '@angular/core';
import {Sentence} from "../model/sentence";
import {SentenceService} from "../service/sentence.service";
import {ActivatedRoute} from "@angular/router";
import {TranslationToPl} from "../model/translationToPl";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  sentence: Sentence | any;
  token: string = '';

  constructor(private userService: UserService, private route: ActivatedRoute, private sentenceService: SentenceService) {
    this.token = sessionStorage.getItem('token') as string;
  }

  ngOnInit(): void {
    this.randomSentence();
  }

  randomSentence() {
    this.sentenceService.getSentenceRandom().subscribe(data => {
      this.sentence = data;
    })
  }

  getContent() {
    this.userService.getSecureContent().subscribe(
      data => {
        console.log(data); // The response is a string, so you can directly access it
      },
      error => console.log(error)
    );
  }
}
