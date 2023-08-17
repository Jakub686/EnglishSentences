import {Component, OnInit} from '@angular/core';
import {Sentence} from "../model/sentence";
import {Router} from "@angular/router";
import {SentenceService} from "../service/sentence.service";
import {RandomDTO} from "../model/randomDTO";
import {AddToFav} from "../model/addToFav";

@Component({
  selector: 'app-sentence-list',
  templateUrl: './sentence-list.component.html',
  styleUrls: ['./sentence-list.component.css']
})
export class SentenceListComponent implements OnInit {
  search: string ='';
  randomDTOS: RandomDTO[] = [];
  addToFavByUserDto: AddToFav = {sentenceId: 0, email: '', favorite: false};
  data: RandomDTO | any;

  token: string ;
  role: string;
  email: string;

  constructor(private sentenceService: SentenceService, private router: Router) {
    this.token = localStorage.getItem('token') as string;
    this.role = localStorage.getItem('role') as string;
    this.email = localStorage.getItem('email') as string;
  }

  ngOnInit() {
    this.getSentencesSimple();
  }

  onSubmit() {
    // this.getSentencesSearch(this.search);
  }

  // private getSentencesSearch(text: string) {
  //   this.sentenceService.getSentenceSimpleSearch(text).subscribe(data => {
  //     this.randomDTOS = data})
  // }

  private getSentencesSimple() {
    this.sentenceService.getSentenceList(this.email, this.token).subscribe(data => {
      this.randomDTOS = data;
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
  }

  addToFav(randomDTOid: number) {
    console.log(randomDTOid)
    if (this.email !== undefined) {
      this.addToFavByUserDto.email = this.email;
      this.addToFavByUserDto.sentenceId = randomDTOid;
      // console.log(this.randomDTOS[randomDTOid].favorite)
      // this.addToFavByUserDto.favorite = this.randomDTOS[randomDTOid].favorite;

      this.sentenceService.addToFav(this.addToFavByUserDto).subscribe(data => {
        this.data = data;
      });
    }
    this.getSentencesSimple();
  }
}
