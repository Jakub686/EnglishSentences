import {Component, OnInit} from '@angular/core';
import {Sentence} from "../model/sentence";
import {ActivatedRoute} from "@angular/router";
import {SentenceService} from "../service/sentence.service";
import {UserService} from "../service/user.service";

@Component({
  selector: 'app-sentence-details',
  templateUrl: './sentence-details.component.html',
  styleUrls: ['./sentence-details.component.css']
})
export class SentenceDetailsComponent implements OnInit {
  id: number | any;
  sentence: Sentence | any;
  token: string;
  email: string;
  role: string;

  constructor(private route: ActivatedRoute, private sentenceService: SentenceService, private userService: UserService) {
    this.token = localStorage.getItem('token') as string;
    this.role = localStorage.getItem('role') as string;
    this.email = localStorage.getItem('email') as string;
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.sentence = new Sentence();
    this.sentenceService.getSentenceById(this.id, this.token).subscribe(data => {
      this.sentence = data;
    });
  }
}
