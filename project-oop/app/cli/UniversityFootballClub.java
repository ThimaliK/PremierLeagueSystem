package cli;

import java.io.Serializable;

public class UniversityFootballClub extends FootballClub implements Serializable {

    	private String universityName;

    	public UniversityFootballClub() {
        	super();
    	}

    	public UniversityFootballClub(String clubName, String clubLocation, String universityName) {
        	super(clubName, clubLocation);
        	this.universityName = universityName;
    	}

    	public String getUniversityName() {
        	return universityName;
    	}

    	public void setUniversityName(String universityName) {
        	this.universityName = universityName;
    	}

    	@Override
    	public String toString() {
        	return "UniversityFootballClub{" +
                	super.toString() +
                	"universityName='" + universityName + '\'' +
                	'}';
    	}
}
