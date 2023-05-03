import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";
import {ActivatedRoute} from "@angular/router";
import {SentenceService} from "../sentence.service";

@Component({
  selector: 'app-sentence-details',
  templateUrl: './sentence-details.component.html',
  styleUrls: ['./sentence-details.component.css']
})
export class SentenceDetailsComponent implements OnInit{
  id:number | any;
  sentence: Sentence | any;
  constructor(private route: ActivatedRoute, private sentenceService: SentenceService) {
  }

  ngOnInit():void {
    this.id = this.route.snapshot.params['id'];
    this.sentence = new Sentence();
    this.sentenceService.getSentenceById(this.id).subscribe(data=>{this.sentence = data;});
    console.log()
  }
}
