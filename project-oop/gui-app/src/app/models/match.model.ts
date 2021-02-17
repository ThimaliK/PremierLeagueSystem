import {FootballClub} from "./footballclub.model";

export interface Match {
  datePlayed: string;
  sportsClub1: FootballClub;
  sportsClub2: FootballClub;
  score1: number;
  score2: number;
}
