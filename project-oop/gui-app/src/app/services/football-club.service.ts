import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/observable';

import { FootballClub } from '../models/footballclub.model';
import 'rxjs/add/operator/map';

@Injectable({
   providedIn: 'root'
})
export class FootballClubService {
  private serviceUrl = '/api/footballclubs';
  private pointsUrl = '/api/footballclubs/sortedbypoints';
  private winsUrl = '/api/footballclubs/sortedbywins';
  private goalsForUrl = '/api/footballclubs/sortedbygf';

  constructor(private http: HttpClient) { }

  getClubs(): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(this.serviceUrl);
  }

  getClubsSortedByPoints(): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(this.pointsUrl);
  }

  getClubsSortedByWins(): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(this.winsUrl);
  }

  getClubsSortedByGF(): Observable<FootballClub[]> {
    return this.http.get<FootballClub[]>(this.goalsForUrl);
  }
}

