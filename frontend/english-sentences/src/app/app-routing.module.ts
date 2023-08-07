import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SentenceListComponent} from "./sentence-list/sentence-list.component";
import {MainComponent} from "./main/main.component";
import {CreateSentenceComponent} from "./create-sentence/create-sentence.component";
import {UpdateSentenceComponent} from "./update-sentence/update-sentence.component";
import {SentenceDetailsComponent} from "./sentence-details/sentence-details.component";
import {LoginComponent} from "./login/login.component";
import {SigninComponent} from "./signin/signin.component";

const routes: Routes = [
  {path: '', redirectTo: 'main', pathMatch: 'full'}, // redirect from '' to main
  {path: 'sentences', component: SentenceListComponent}, // if http://localhost:4200/sentences show SentenceListComponent
  {path: 'create-sentence', component: CreateSentenceComponent},
  {path: 'main', component: MainComponent},
  {path: 'update-sentence/:id', component: UpdateSentenceComponent},
  {path: 'details-sentence/:id', component: SentenceDetailsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signin', component: SigninComponent},
  {path: '**', redirectTo: 'main'}, // redirect from '**' to main
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
