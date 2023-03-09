import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";
import {Router} from "@angular/router";
import {SentenceService} from "../sentence.service";

@Component({
  selector: 'app-sentence-list',
  templateUrl: './sentence-list.component.html',
  styleUrls: ['./sentence-list.component.css']
})
export class SentenceListComponent implements OnInit {
  sentence: Sentence = new Sentence();
  sentences: Sentence[] = [];
  constructor(private sentenceService: SentenceService, private router: Router) {
  }
  ngOnInit() {
    this.getSentencesSimple();
  }
  onSubmit(){
    console.log(this.sentence);
    console.log(this.sentence.textEn);
    this.getSentencesSearch(this.sentence.textEn);
  }

  showList(){
    this.getSentencesSimple();
  }

  private getSentencesSearch(text: string){
    this.sentenceService.getSentenceSimpleSearch(text).subscribe(data =>{
      console.log(data);
      this.sentences = data;
    })
  }

  private getSentencesSimple(){
    this.sentenceService.getSentenceSimpleList().subscribe(data =>{
      this.sentences = data;
    })
  }

  updateSentence(id: number){
    this.router.navigate(['update-sentence',id]);
  }

  deleteSentence(id: number){
    this.sentenceService.deleteSentence(id).subscribe(data =>{this.getSentencesSimple();
      console.log(data);
    });
  }

  detailsSentence(id: number){
    this.router.navigate(['details-sentence',id])
    this.sentenceService.detailSentence(id).subscribe();
  }
}
