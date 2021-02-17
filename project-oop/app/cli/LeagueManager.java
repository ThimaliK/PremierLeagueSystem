package cli;

import java.io.IOException;
import java.time.LocalDate;

//all methods that is common to all league managers are included here
public interface LeagueManager {

	void addClub(SportsClub sportsClub);
    	void deleteClub(String clubName);
    	SportsClub statsOfClub(String clubName);
    	void displayLeagueTable();
    	void addPlayedMatch(LocalDate date, String clubName1, String clubName2, int score1, int score2);
    	void saveDataToFile(String fileName) throws IOException;
}
