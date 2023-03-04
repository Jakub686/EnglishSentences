import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";
import {SentenceService} from "../sentence.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit{

  sentence: Sentence | any;

  constructor(private route: ActivatedRoute, private sentenceService: SentenceService) {}

  ngOnInit():void {
    this.randomSentence();
  }

  randomSentence(){
      this.sentenceService.getSentenceRandom().subscribe(data =>{
        this.sentence = data;
      })
    }


}
