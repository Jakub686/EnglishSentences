import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";
import {SentenceService} from "../sentence.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-sentence',
  templateUrl: './create-sentence.component.html',
  styleUrls: ['./create-sentence.component.css']
})
export class CreateSentenceComponent implements OnInit{

  sentence: Sentence = new Sentence();
  constructor(private sentenceService: SentenceService,
              private router: Router) {
  }

  ngOnInit(){}

  saveSentence(){
    this.sentenceService.createSentenceSimple(this.sentence).subscribe(data =>{
    console.log(data);
    },
      error => console.log(error));
  }
  goToSentenceList(){
    this.router.navigate(['/sentences'])
  }

  onSubmit(){
    console.log(this.sentence);
    console.log(this.sentence.text);
    this.saveSentence();
    this.goToSentenceList();

  }
}


