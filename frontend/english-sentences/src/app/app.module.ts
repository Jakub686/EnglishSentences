import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from "@angular/material/button";
import { MainComponent } from './main/main.component';
import { SentenceListComponent } from './sentence-list/sentence-list.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { CreateSentenceComponent } from './create-sentence/create-sentence.component';
import { FormsModule } from "@angular/forms";
import { UpdateSentenceComponent } from './update-sentence/update-sentence.component';
import { SentenceDetailsComponent } from './sentence-details/sentence-details.component';
import { LoginComponent } from './login/login.component';
import { SigninComponent } from './signin/signin.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    SentenceListComponent,
    CreateSentenceComponent,
    UpdateSentenceComponent,
    SentenceDetailsComponent,
    LoginComponent,
    SigninComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatButtonModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
