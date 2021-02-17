import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FootballClubService } from './services/football-club.service';
import { MatchService } from './services/match.service';
import { SingleMatchService } from "./services/singlematch.service";
import { FindMatchService} from "./services/find-match.service";

import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatNativeDateModule } from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {TextFieldModule} from '@angular/cdk/text-field';
import {MatInputModule} from "@angular/material/input";

import { AppComponent } from './app.component';
import { LeagueTableComponent } from './components/league-table/league-table.component';
import { MatchTableComponent } from './components/match-table/match-table.component';
import { GenerateMatchComponent } from './components/generate-match/generate-match.component';
import { FindMatchesComponent } from './components/find-matches/find-matches.component';
import { HomeComponent } from './components/home/home.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    AppComponent,
    LeagueTableComponent,
    MatchTableComponent,
    GenerateMatchComponent,
    FindMatchesComponent,
    HomeComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        MatTableModule,
        BrowserAnimationsModule,
        MatTableModule,
        MatToolbarModule,
        MatNativeDateModule,
        MatFormFieldModule,
        MatCardModule,
        MatButtonModule,
        TextFieldModule,
        MatInputModule,
        FormsModule
    ],
  providers: [FootballClubService, MatchService, SingleMatchService, FindMatchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
