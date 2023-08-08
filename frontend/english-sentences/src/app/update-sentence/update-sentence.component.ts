import {Component, OnInit} from '@angular/core';
import {Sentence} from "../model/sentence";
import {ActivatedRoute, Router } from "@angular/router";
import {SentenceService} from "../service/sentence.service";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-update-sentence',
  templateUrl: './update-sentence.component.html',
  styleUrls: ['./update-sentence.component.css']
})
export class UpdateSentenceComponent implements OnInit{
  id:number = 0;
  sentence: Sentence = {id: 0, textEn:"",textPl:""};
  token: string;
  email: string;
  role: string;

  constructor(private sentenceService: SentenceService, private route: ActivatedRoute, private router: Router, private userService: UserService) {
    this.token = localStorage.getItem('token') as string;
    this.role = localStorage.getItem('role') as string;
    this.email = localStorage.getItem('email') as string;
  }

  ngOnInit(): void{
    this.id = this.route.snapshot.params['id'];
    this.sentenceService.getSentenceById(this.id, this.token).subscribe(data =>{
      this.sentence = data;
    }, error => console.log(error));
  }

  onSubmit(){
    this.sentenceService.updateSentence(this.id, this.sentence).subscribe( data =>{
        this.goToSentenceList();
      }
      , error => console.log(error));
  }

  goToSentenceList(){
    this.router.navigate(['/sentences'])
  }

}

export function aaa(){
  return
}
