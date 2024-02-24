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
  randomDTO: RandomDTO = {id: 0, textEn: '', textPl: '', favorite: true};
  data: RandomDTO | any;
  addToFavByUserDto: AddToFav = {sentenceId: 0, email: '', favorite: true};
  token: string;
  email: string;
  role: string;
  fav = false;

  constructor(private userService: UserService, private route: ActivatedRoute, private sentenceService: SentenceService) {
    this.token = localStorage.getItem('token') as string;
    this.role = localStorage.getItem('role') as string;
    this.email = localStorage.getItem('email') as string;
  }

  ngOnInit(): void {
    this.randomSentence();
  }

  randomSentence() {
    if (this.email === null || this.email === '' || this.email === undefined) {
      this.sentenceService.getSentenceRandom().subscribe(data => {
        this.randomDTO = data;
      });
    }

    if (this.email !== null) {
      this.sentenceService.getSentenceRandomForUser(this.email, this.fav, this.token).subscribe(data => {
        this.randomDTO = data;
      });
    }

  }

  addToFav(randomDTOid: number) {
    if (this.email !== undefined) {
      this.addToFavByUserDto.email = this.email;
      this.addToFavByUserDto.sentenceId = randomDTOid;
      this.addToFavByUserDto.favorite = this.randomDTO.favorite;

      this.sentenceService.addToFav(this.addToFavByUserDto, this.token).subscribe(data => {
        this.data = data;
        this.randomDTO.favorite = !this.randomDTO.favorite;
      });
    }
  }

  toggleFavorite() {
    // Update this.fav when the checkbox state changes
    // You can perform additional logic here if needed
    console.log('Toggle switch state changed:', this.fav);
  }

  readText() {
    if ('speechSynthesis' in window) {
      const speech = new SpeechSynthesisUtterance(this.randomDTO.textEn);
      window.speechSynthesis.speak(speech);
    } else {
      alert('Text-to-speech is not supported in your browser.');
    }
  }

}
