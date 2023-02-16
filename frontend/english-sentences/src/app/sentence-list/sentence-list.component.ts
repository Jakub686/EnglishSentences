import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";
import { SentenceService } from  "../sentence.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sentence-list',
  templateUrl: './sentence-list.component.html',
  styleUrls: ['./sentence-list.component.css']
})
export class SentenceListComponent implements OnInit {

  sentences?: Sentence[];
  constructor(private sentenceService: SentenceService,
              private router: Router) {
  }
  ngOnInit() {
    this.getSentencesSimple();
  }

  private getSentencesSimple(){
    this.sentenceService.getSentenceSimpleList().subscribe(data =>{
      this.sentences = data;
    })
  }

  updateSentence(id: number){
    this.router.navigate(['update-sentence',id]);
  }
}
