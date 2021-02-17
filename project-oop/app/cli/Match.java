package cli;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

public class Match implements Comparable<Match>, Serializable{

		private LocalDate datePlayed;
		private SportsClub sportsClub1;   //a match can exist between any type of 2 SportsClubs
    	private SportsClub sportsClub2;
    	private int score1;
    	private int score2;

    	public Match(LocalDate datePlayed, SportsClub sportsClub1, SportsClub sportsClub2, int score1, int score2) {
        	this.datePlayed = datePlayed;
        	this.sportsClub1 = sportsClub1;
        	this.sportsClub2 = sportsClub2;
        	this.score1 = score1;
		this.score2 = score2;
    	}

    	public LocalDate getDatePlayed() {
        	return datePlayed;
    	}

    	public void setDatePlayed(LocalDate datePlayed) {
        	this.datePlayed = datePlayed;
    	}

    	public SportsClub getSportsClub1() {
        	return sportsClub1;
    	}

    	public void setSportsClub1(SportsClub sportsClub1) {
        	this.sportsClub1 = sportsClub1;
    	}

    	public SportsClub getSportsClub2() {
        	return sportsClub2;
    	}

    	public void setSportsClub2(SportsClub sportsClub2) {
        	this.sportsClub2 = sportsClub2;
    	}

    	public int getScore1() {
        	return score1;
    	}

    	public void setScore1(int score1) {
        	this.score1 = score1;
    	}

    	public int getScore2() {
        	return score2;
    	}

    	public void setScore2(int score2) {
        	this.score2 = score2;
    	}

    	@Override
    	public String toString() {
        	return "Match{" +
                	"datePlayed=" + datePlayed +
                	", sportsClub1=" + sportsClub1 +
                	", sportsClub2=" + sportsClub2 +
                	", score1=" + score1 +
                	", score2=" + score2 +
                	'}';
    	}

    	@Override
    	public boolean equals(Object o) {
        	if (this == o) return true;
        	if (o == null || getClass() != o.getClass()) return false;
        	Match match = (Match) o;
        	return score1 == match.score1 &&
                	score2 == match.score2 &&
                	Objects.equals(datePlayed, match.datePlayed) &&
                	Objects.equals(sportsClub1, match.sportsClub1) &&
                	Objects.equals(sportsClub2, match.sportsClub2);
    	}

    	@Override
    	public int hashCode() {
        	return Objects.hash(datePlayed, sportsClub1, sportsClub2, score1, score2);
    	}

    	@Override
    	public int compareTo(Match match) {
        	return this.getDatePlayed().compareTo(match.getDatePlayed());
    	}
}
