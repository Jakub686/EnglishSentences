import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";
import {Router} from "@angular/router";
import {SentenceService} from "../service/sentence.service";

@Component({
  selector: 'app-sentence-list',
  templateUrl: './sentence-list.component.html',
  styleUrls: ['./sentence-list.component.css']
})
export class SentenceListComponent implements OnInit {
  search: string ="";
  sentences: Sentence[] = [];

  constructor(private sentenceService: SentenceService, private router: Router) {
  }

  ngOnInit() {
    this.getSentencesSimple();
  }

  onSubmit() {
    this.getSentencesSearch(this.search);
  }

  showList() {
    this.getSentencesSimple();
  }

  private getSentencesSearch(text: string) {
    this.sentenceService.getSentenceSimpleSearch(text).subscribe(data => {this.sentences = data})
  }

  private getSentencesSimple() {
    this.sentenceService.getSentenceSimpleList().subscribe(data => {
      this.sentences = data;
    })
  }

  updateSentence(id: number) {
    this.router.navigate(['update-sentence', id]);
  }

  deleteSentence(id: number) {
    this.sentenceService.deleteSentence(id).subscribe(data => {
      this.getSentencesSimple();
      console.log(data);
    });
  }

  detailsSentence(id: number) {
    this.router.navigate(['details-sentence', id])
    this.sentenceService.detailSentence(id).subscribe();
  }
}
