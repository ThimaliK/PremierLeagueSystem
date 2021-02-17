package cli;

import java.io.Serializable;
import java.util.Objects;

public class FootballClub extends SportsClub implements Comparable<FootballClub>, Serializable {

    	private int goalsScored;
    	private int goalsAgainst;
    	private int noOfMatches;
    	private int points;

    	public FootballClub() {
        	super();
    	}

    	public FootballClub(String name, String location) {
        	super(name, location);
    	}

    	public FootballClub(String name, String location, int wins, int defeats, int draws, int goalsScored, int goalsAgainst,
							int noOfMatches, int points) {

        	super(name, location, wins, defeats, draws);
        	this.goalsScored = goalsScored;
        	this.goalsAgainst = goalsAgainst;
        	this.noOfMatches = noOfMatches;
        	this.points = points;
    	}

    	public int getGoalsScored() {
        	return this.goalsScored;
    	}

    	public void setGoalsScored(int goalsFor) {
        	this.goalsScored = goalsFor;
    	}

    	public int getGoalsAgainst() {
        	return this.goalsAgainst;
    	}

    	public void setGoalsAgainst(int goalsAgainst) {
        	this.goalsAgainst = goalsAgainst;
    	}

    	public int getNoOfMatches() {
        	return this.noOfMatches;
    	}

    	public void setNoOfMatches(int noOfMatches) {
        	this.noOfMatches = noOfMatches;
    	}

    	public int getPoints() {
        	return this.points;
    	}

    	public void setPoints(int points) {
        	this.points = points;
    	}

    	public int getGoalDifference() {
        	return this.goalsScored - this.goalsAgainst;
    	}

    	@Override
    	public String toString() {
        	return "FootballClub{" +
                	super.toString()+
                	"goalsScored=" + goalsScored +
                	", goalsAgainst=" + goalsAgainst +
                	", noOfMatches=" + noOfMatches +
                	", points=" + points +
                	'}';
    	}

    	@Override
    	public int compareTo(FootballClub footballClub) {
        	return Integer.compare(this.points, footballClub.points);
    	}

    	@Override
    	public boolean equals(Object o) {
        	if (this == o) return true;
        	if (o == null || getClass() != o.getClass()) return false;
        	if (!super.equals(o)) return false;
        	FootballClub that = (FootballClub) o;
        	return goalsScored == that.goalsScored &&
                	goalsAgainst == that.goalsAgainst &&
                	noOfMatches == that.noOfMatches &&
                	points == that.points;
    	}

    	@Override
    	public int hashCode() {
        	return Objects.hash(super.hashCode(), goalsScored, goalsAgainst, noOfMatches, points);
    	}

}
