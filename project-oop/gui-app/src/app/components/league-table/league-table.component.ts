import { Component, OnInit } from '@angular/core';
import { FootballClubService } from '../../services/football-club.service';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';
import { FootballClub } from '../../models/footballclub.model';

@Component({
  selector: 'app-league-table',
  templateUrl: './league-table.component.html',
  styleUrls: ['./league-table.component.css']
})

export class LeagueTableComponent implements OnInit {

  dataSource = new FootballClubDataSource(this.footballClubService);
  displayedColumns = ['position', 'name', 'noOfMatches', 'wins', 'draws', 'defeats', 'goalsScored', 'goalsAgainst',
    'goalDifference', 'points'];
  constructor(private footballClubService: FootballClubService) { }

  sortClubsByPoints() {
    // @ts-ignore
    this.dataSource = new FootballClubPointsDataSource(this.footballClubService)
  }

  sortClubsByWins() {
    // @ts-ignore
    this.dataSource = new FootballClubWinsDataSource(this.footballClubService)
  }

  sortClubsByGF() {
    // @ts-ignore
    this.dataSource = new FootballClubGFDataSource(this.footballClubService)
  }

  // tslint:disable-next-line:typedef
  ngOnInit() {
  }
}

export class FootballClubDataSource extends DataSource<any> {
  constructor(private footballClubService: FootballClubService) {
    super();
  }

  connect(): Observable<FootballClub[]> {
    return this.footballClubService.getClubs();
  }
  // tslint:disable-next-line:typedef
  disconnect() {}
}

export class FootballClubPointsDataSource extends DataSource<any> {
  constructor(private footballClubService: FootballClubService) {
    super();
  }
  connect(): Observable<FootballClub[]> {
    return this.footballClubService.getClubsSortedByPoints();
  }
  // tslint:disable-next-line:typedef
  disconnect() {}
}

export class FootballClubWinsDataSource extends DataSource<any> {
  constructor(private footballClubService: FootballClubService) {
    super();
  }
  connect(): Observable<FootballClub[]> {
    return this.footballClubService.getClubsSortedByWins();
  }
  // tslint:disable-next-line:typedef
  disconnect() {}
}

export class FootballClubGFDataSource extends DataSource<any> {
  constructor(private footballClubService: FootballClubService) {
    super();
  }
  connect(): Observable<FootballClub[]> {
    return this.footballClubService.getClubsSortedByGF();
  }
  // tslint:disable-next-line:typedef
  disconnect() {}
}
