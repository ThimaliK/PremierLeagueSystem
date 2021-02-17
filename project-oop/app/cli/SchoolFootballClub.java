package cli;

import java.io.Serializable;

public class SchoolFootballClub extends FootballClub implements Serializable {

    	private String schoolName;

    	public SchoolFootballClub(){
        	super();
    	}

    	public SchoolFootballClub(String clubName, String clubLocation, String schoolName) {
			super(clubName, clubLocation);
        	this.schoolName = schoolName;
    	}

    	public String getSchoolName() {
        	return schoolName;
    	}

    	public void setSchoolName(String schoolName) {
        	this.schoolName = schoolName;
    	}

    	@Override
    	public String toString() {
        	return "SchoolFootballClub{" +
                	super.toString() +
                	"schoolName='" + schoolName + '\'' +
                	'}';
    	}
}
