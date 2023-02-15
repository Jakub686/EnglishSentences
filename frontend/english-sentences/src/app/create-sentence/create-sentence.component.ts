import {Component, OnInit} from '@angular/core';
import {Sentence} from "../sentence";

@Component({
  selector: 'app-create-sentence',
  templateUrl: './create-sentence.component.html',
  styleUrls: ['./create-sentence.component.css']
})
export class CreateSentenceComponent implements OnInit{

  sentence: Sentence = new Sentence();
  constructor() {
  }

  ngOnInit(){}

  onSubmit(){

  }
}


