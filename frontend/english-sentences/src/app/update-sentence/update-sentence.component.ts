import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";
import {ActivatedRoute, Router } from "@angular/router";
import {SentenceService} from "../detailsSentence";

@Component({
  selector: 'app-update-sentence',
  templateUrl: './update-sentence.component.html',
  styleUrls: ['./update-sentence.component.css']
})
export class UpdateSentenceComponent implements OnInit{
  id:number = 0;
  sentence: Sentence = new Sentence();
  constructor(private sentenceService: SentenceService, private route: ActivatedRoute, private router: Router) {
  }
  ngOnInit(): void{
    this.id = this.route.snapshot.params['id'];
    this.sentenceService.getSentenceById(this.id).subscribe(data =>{
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
