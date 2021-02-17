import { Component, OnInit } from '@angular/core';
import { SingleMatchService } from "../../services/singlematch.service";
import 'rxjs/add/observable/of';

@Component({
  selector: 'app-generate-match',
  templateUrl: './generate-match.component.html',
  styleUrls: ['./generate-match.component.css'],
})
export class GenerateMatchComponent implements OnInit {

  matchDetails = ""

  constructor(private singleMatchService: SingleMatchService) {

  }

  generateMatch() {
    this.singleMatchService.getMatch()
       .subscribe(data => this.matchDetails = data);
  }

  ngOnInit(): void {
  }
}

