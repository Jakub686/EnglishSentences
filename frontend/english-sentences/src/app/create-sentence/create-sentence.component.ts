import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";
import {Router} from "@angular/router";
import {SentenceService} from "../service/sentence.service";
import {TranslationToPl} from "../translationToPl";

@Component({
  selector: 'app-create-sentence',
  templateUrl: './create-sentence.component.html',
  styleUrls: ['./create-sentence.component.css']
})
export class CreateSentenceComponent{

  sentence: Sentence = {id: 0, textEn:"",translationToPl:[{id:0,textPl:""},{id:1,textPl:""}]};
  constructor(private sentenceService: SentenceService, private router: Router) {
  }

  onSubmit(){
    console.log(this.sentence);
    console.log(this.sentence.textEn);
    console.log(this.sentence.translationToPl[0].textPl);
    this.saveSentence();
    this.goToSentenceList();
  }
  saveSentence(){
    this.sentenceService.createSentenceSimple(this.sentence).subscribe(data =>{
    console.log(data);
    },
      error => console.log(error));
  }

  goToSentenceList(){
    this.router.navigate(['/sentences'])
  }
}


