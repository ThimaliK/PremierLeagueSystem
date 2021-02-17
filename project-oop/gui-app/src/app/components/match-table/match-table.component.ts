import { Component, OnInit } from '@angular/core';
import { MatchService } from '../../services/match.service';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import {DataSource} from '@angular/cdk/collections';
import { Match } from '../../models/match.model';


@Component({
  selector: 'app-match-table',
  templateUrl: './match-table.component.html',
  styleUrls: ['./match-table.component.css']
})
export class MatchTableComponent implements OnInit {

  dataSource = new MatchDataSource(this.matchService);
  displayedColumns = ['datePlayed', 'sportsClub1', 'score1', 'score2', 'sportsClub2'];
  constructor(private matchService: MatchService) { }

  ngOnInit() {
  }
}
export class MatchDataSource extends DataSource<any> {
  constructor(private matchService: MatchService) {
    super();
  }
  connect(): Observable<Match[]> {
    return this.matchService.getMatches();
  }
  disconnect() {}
}
