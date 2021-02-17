import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Match} from "../models/match.model";

@Injectable()
export class FindMatchService {

  private matchesUrl = '/api/matches';

  constructor(private http: HttpClient) {}

  getMatchesOfDay(stringDate: string): Observable<Match[]> {

    if (stringDate=="") {
       return this.http.get<Match[]>(this.matchesUrl)
    }
    return this.http.get<Match[]>(this.matchesUrl + "/"+stringDate);
  }

}
