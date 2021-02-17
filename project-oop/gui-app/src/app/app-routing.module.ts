import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LeagueTableComponent } from './components/league-table/league-table.component';
import { MatchTableComponent } from './components/match-table/match-table.component';
import { GenerateMatchComponent } from './components/generate-match/generate-match.component';
import { FindMatchesComponent } from './components/find-matches/find-matches.component';
import { HomeComponent } from "./components/home/home.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'pltable', component: LeagueTableComponent},
  {path: 'matchtable', component: MatchTableComponent},
  {path: 'generatematch', component: GenerateMatchComponent},
  {path: 'findmatches', component: FindMatchesComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
