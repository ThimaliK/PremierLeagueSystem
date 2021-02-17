import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/observable';

import { Match } from '../models/match.model';
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class MatchService {
  private serviceUrl = '/api/matches';

  constructor(private http: HttpClient) { }

  getMatches(): Observable<Match[]> {
    return this.http.get<Match[]>(this.serviceUrl);
  }
}
