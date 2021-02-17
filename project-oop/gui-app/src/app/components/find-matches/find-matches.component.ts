import { Component, OnInit } from '@angular/core';
import {DataSource} from "@angular/cdk/collections";
import {Observable} from "rxjs";
import {Match} from "../../models/match.model";
import 'rxjs/add/operator/map';
import {FindMatchService} from "../../services/find-match.service";

@Component({
  selector: 'app-find-matches',
  templateUrl: './find-matches.component.html',
  styleUrls: ['./find-matches.component.css']
})
export class FindMatchesComponent implements OnInit {

  date = "";
  message1 = "Wrong date input! Please enter a correct date...";
  displayMessage1 = false
  regex1: RegExp = /^[+ 0-9]{2}$/
  regex2: RegExp = /^[+ 0-9]{4}$/
  displayTable = false
  dataSource = new MatchesDataSource(this.findMatchService, this.date);
  displayedColumns = ['sportsClub1', 'score1', 'score2', 'sportsClub2'];

    constructor(private findMatchService: FindMatchService) {
  }

  ngOnInit(): void {

  }

  sendDate(year: string, month: string, day: string) {

    day = day.trim()
    month = month.trim()
    year = year.trim()

    if(day.length==1) {
      day = "0"+day
    }

    if(month.length==1) {
      month = "0"+month
    }

    if(!day.match(this.regex1) || !month.match(this.regex1) || !year.match(this.regex2)) {
      this.displayMessage1 = true
      return
    }

    this.displayMessage1 = false
    this.date = year+"-"+month+"-"+day
    this.dataSource = new MatchesDataSource(this.findMatchService, this.date)
    this.displayTable = true

    }

}

export class MatchesDataSource extends DataSource<any> {

  constructor(private findMatchService: FindMatchService, private date: string) {
    super();
  }
  connect(): Observable<Match[]> {
    return this.findMatchService.getMatchesOfDay(this.date);
  }
  disconnect() {}
}

