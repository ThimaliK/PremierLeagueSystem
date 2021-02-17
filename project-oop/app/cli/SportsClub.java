package cli;

import java.io.Serializable;
import java.util.Objects;

public abstract class SportsClub implements Serializable {

    	private String name;
    	private String location;
    	private int wins;
    	private int defeats;
    	private int draws;

    	public SportsClub() {
    	}

    	public SportsClub(String name, String location) {
        	this.name = name;
        	this.location = location;
    	}

    	public SportsClub(String name, String location, int wins, int defeats, int draws) {
        	this.name = name;
        	this.location = location;
        	this.wins = wins;
        	this.defeats = defeats;
        	this.draws = draws;
    	}

    	public String getName() {
        	return this.name;
    	}

    	public void setName(String name) {
        	this.name = name;
    	}

    	public String getLocation() {
        	return this.location;
    	}

    	public void setLocation(String location) {
        	this.location = location;
    	}

    	public int getWins() {
        	return this.wins;
    	}

    	public void setWins(int wins) {
        	this.wins = wins;
    	}

    	public int getDefeats() {
        	return this.defeats;
    	}

    	public void setDefeats(int defeats) {
        	this.defeats = defeats;
    	}

    	public int getDraws() {
        	return this.draws;
    	}

    	public void setDraws(int draws) {
        	this.draws = draws;
    	}

    	@Override
    	public String toString() {
        	return "SportsClub{" +
                	"name='" + name + '\'' +
                	", location='" + location + '\'' +
                	", wins=" + wins +
                	", defeats=" + defeats +
                	", draws=" + draws +
                	'}';
    	}

    	@Override
    	public boolean equals(Object o) {
        	if (this == o) return true;
        	if (o == null || getClass() != o.getClass()) return false;
        	SportsClub that = (SportsClub) o;
        	return wins == that.wins &&
                	defeats == that.defeats &&
                	draws == that.draws &&
                	name.equals(that.name) &&
                	location.equals(that.location);
    	}

    	@Override
    	public int hashCode() {
        	return Objects.hash(name, location, wins, defeats, draws);
    	}
}
