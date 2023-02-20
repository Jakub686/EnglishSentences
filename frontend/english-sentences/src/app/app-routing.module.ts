import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SentenceListComponent} from "./sentence-list/sentence-list.component";
import {MainComponent} from "./main/main.component";
import {CreateSentenceComponent} from "./create-sentence/create-sentence.component";
import {UpdateSentenceComponent} from "./update-sentence/update-sentence.component";
import {SentenceDetailsComponent} from "./sentence-details/sentence-details.component";

const routes: Routes = [
  {path: 'sentences', component: SentenceListComponent}, // if http://localhost:4200/sentences show SentenceListComponent
  {path: 'create-sentence', component: CreateSentenceComponent},
  {path: 'main', component: MainComponent},
  {path: '', redirectTo: 'main', pathMatch: 'full'}, // redirect from '' to sentences
  {path: 'update-sentence/:id', component: UpdateSentenceComponent},
  {path: 'details-sentence/:id', component: SentenceDetailsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
