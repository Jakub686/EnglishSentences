import {Component, OnInit} from '@angular/core';
import {Sentence} from "../model/sentence";
import {SentenceService} from "../service/sentence.service";
import {ActivatedRoute} from "@angular/router";
import {TranslationToPl} from "../model/translationToPl";
import {UserService} from "../service/user.service";
import {RandomDTO} from "../model/randomDTO";
import {AddToFavByUserDto} from "../model/addToFavByUserDto";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  sentence: Sentence | any;
  randomDTO: RandomDTO | any;
  addToFavByUserDto: AddToFavByUserDto = {sentenceId:0, email:''};
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
    this.sentenceService.getSentenceRandom().subscribe(data => {
      this.randomDTO = data;
    })
  }

  addToFav(randomDTOid: number) {
    if (this.email !== undefined) {
      this.addToFavByUserDto.email = this.email;
      this.addToFavByUserDto.sentenceId = randomDTOid;
      console.log(this.addToFavByUserDto)

      this.sentenceService.addToFav(this.addToFavByUserDto).subscribe(data => {
        this.randomDTO = data;
      });
    }
  }

}
