import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/observable';
import 'rxjs/add/operator/map';

@Injectable({
  providedIn: 'root'
})
export class SingleMatchService {
  private serviceUrl = '/api/createdmatch';

  constructor(private http: HttpClient) { }

  getMatch(): Observable<string> {
    return this.http.get<string>(this.serviceUrl);
  }
}
