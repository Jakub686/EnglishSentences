import {Component, OnInit} from '@angular/core';
import {Sentence} from "../model/sentence";
import {SentenceService} from "../service/sentence.service";
import {ActivatedRoute} from "@angular/router";
import {TranslationToPl} from "../model/translationToPl";
import {UserService} from "../service/user.service";
import {RandomDTO} from "../model/randomDTO";
import {AddToFav} from "../model/addToFav";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  sentence: Sentence | any;
  randomDTO: RandomDTO | any;
  data: RandomDTO | any;
  addToFavByUserDto: AddToFav = {sentenceId:0, email:'',favorite:false};
  token: string;
  email: string;
  role: string;

  constructor(private userService: UserService, private route: ActivatedRoute, private sentenceService: SentenceService) {
    this.token = localStorage.getItem('token') as string;
    this.role = localStorage.getItem('role') as string;
    this.email = localStorage.getItem('email') as string;
  }

  ngOnInit(): void {
    this.randomSentence();
  }

  randomSentence() {
    this.sentenceService.getSentenceRandom(this.email).subscribe(data => {
      this.randomDTO = data;
    })
  }

  addToFav(randomDTOid: number) {
    if (this.email !== undefined) {
      this.addToFavByUserDto.email = this.email;
      this.addToFavByUserDto.sentenceId = randomDTOid;
      this.addToFavByUserDto.favorite = this.randomDTO.favorite;

      this.sentenceService.addToFav(this.addToFavByUserDto).subscribe(data => {
        this.data = data;
        console.log(this.data)
        this.randomDTO.favorite = this.data.favorite;
      });
    }
  }

}
