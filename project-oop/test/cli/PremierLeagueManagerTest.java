package cli;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PremierLeagueManagerTest {

    private static final String filePath = "PLDataForTest.txt";
    private final PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    private final FootballClub footballClubA = new FootballClub("liverpool", "liverpool, united kingdom");
    private final FootballClub footballClubB = new FootballClub("manchester city", "manchester, united kingdom");

    @Test
    void addClubTest() {
        int expected = premierLeagueManager.getAllFootballClubs().size()+1;
        premierLeagueManager.addClub(footballClubA);
        assertEquals(expected, premierLeagueManager.getAllFootballClubs().size(), "addClub method should" +
                "increase the size of allFootballClubs by 1");
    }

    @Test
    void deleteClubTest() {
        int expected = premierLeagueManager.getAllFootballClubs().size()-1;
        premierLeagueManager.deleteClub("liverpool");
        assertEquals(expected, premierLeagueManager.getAllFootballClubs().size(), "deleteClub method should" +
                " decrease the size of allFootballClubs by 1");
    }

    @Test
    void statsOfClubTest() {
        premierLeagueManager.addClub(footballClubA);
        FootballClub actualFootballClub = premierLeagueManager.statsOfClub("liverpool");
        assertSame(footballClubA, actualFootballClub, "statsOfClub method should return the same" +
                " FootballClub object");
    }

    @Test
    void updateStatsAfterMatchTest() {

        int score1 = 2;
        int score2 = 1;

        premierLeagueManager.addClub(footballClubA);
        premierLeagueManager.addClub(footballClubB);

        premierLeagueManager.updateStatsAfterMatch(footballClubA, footballClubB, score1, score2);

        assertEquals(2, footballClubA.getGoalsScored(), "updateStatsAfterMatch should increase the number" +
                " of goals scored by the first club by 2");
        assertEquals(1, footballClubA.getGoalsAgainst(), "updateStatsAfterMatch should increase the number" +
                " of goals against of the first club by 1");

        assertEquals(1, footballClubB.getGoalsScored(), "updateStatsAfterMatch should increase the number" +
                " of goals scored by the second club by 1");
        assertEquals(2, footballClubB.getGoalsAgainst(), "updateStatsAfterMatch should increase the number" +
                " of goals against of the second club by 2");

        assertEquals(1, footballClubA.getNoOfMatches(), "updateStatsAfterMatch should increase the number" +
                " of matches of the first club by one");

        assertEquals(1, footballClubB.getNoOfMatches(), "updateStatsAfterMatch should increase the number" +
                " of matches of the second club by one");

        assertEquals(1, footballClubA.getWins(), "updateStatsAfterMatch should increase the number" +
                " of wins of the winning club by one");
        assertEquals(0, footballClubB.getWins(), "updateStatsAfterMatch should keep the number of wins" +
                " of the losing club unchanged");

        assertEquals(0, footballClubA.getDraws(), "updateStatsAfterMatch should keep the number of draws" +
                " unchanged");
        assertEquals(0, footballClubB.getDraws(), "updateStatsAfterMatch should keep the number of draws" +
                " unchanged");

        assertEquals(0, footballClubA.getDefeats(), "updateStatsAfterMatch should keep the number" +
                " of defeats of the winning club unchanged");
        assertEquals(1, footballClubB.getDefeats(), "updateStatsAfterMatch should increase the number of " +
                "defeats of the losing club by 1");

        assertEquals(3, footballClubA.getPoints(), "updateStatsAfterMatch should increase the number" +
                " of points of the winning team by 3");

        assertEquals(0, footballClubB.getPoints(), "updateStatsAfterMatch should keep the number" +
                " of points of the losing unchanged");
    }

    @Test
    void addPlayedMatchTest() {

        int expected = premierLeagueManager.getAllMatches().size()+1;

        premierLeagueManager.addClub(footballClubA);
        premierLeagueManager.addClub(footballClubB);

        int score1 = 2;
        int score2 = 1;
        LocalDate localDate = LocalDate.now();

        premierLeagueManager.addPlayedMatch(localDate, "liverpool", "manchester city", score1, score2);

        assertEquals(expected, premierLeagueManager.getAllMatches().size(), "addPlayedMatch method should" +
                "increase the size of allMatches by 1");

    }

    @Test
    void saveAndLoadFileTest() throws IOException {
        premierLeagueManager.addClub(footballClubA);
        premierLeagueManager.addClub(footballClubB);
        premierLeagueManager.addPlayedMatch(LocalDate.now(), "liverpool", "manchester city", 1,2);
        premierLeagueManager.saveDataToFile(filePath);

        PremierLeagueManager actualPremierLeagueManager = PremierLeagueManager.loadDataFromFile(filePath);

        assertIterableEquals(premierLeagueManager.getAllFootballClubs(), actualPremierLeagueManager.getAllFootballClubs(),
                "The saved PremierLeagueManager object should contain the same FootballClubs");
        assertIterableEquals(premierLeagueManager.getAllMatches(), actualPremierLeagueManager.getAllMatches(),
                "The saved PremierLeagueManager object should contain the same FootballClubs");
    }


    @Test
    void generateMatchTest() {
        int expected = premierLeagueManager.getAllMatches().size()+1;
        premierLeagueManager.generateMatch();
        assertEquals(expected, premierLeagueManager.getAllMatches().size(), "random match should be generated" +
                "and added to allMatches. allMatches should increase size by one");
    }

    @Test
    void allMatchesOnADayTest() {

        boolean actual = true;

        premierLeagueManager.addClub(footballClubA);
        premierLeagueManager.addClub(footballClubB);

        LocalDate pastDate = LocalDate.of(2020, 12, 12);
        LocalDate today = LocalDate.now();

        Match match1 = new Match(pastDate, footballClubA, footballClubB, 1,2);
        Match match2 = new Match(today, footballClubB, footballClubA, 3,2);

        premierLeagueManager.getAllMatches().add(match1);
        premierLeagueManager.getAllMatches().add(match2);

        ArrayList<Match> matchesOnADay = premierLeagueManager.allMatchesOnADay(LocalDate.now());
        for (Match match: matchesOnADay) {
            if(!match.getDatePlayed().equals(LocalDate.now())) {
                actual = false;
            }
        }

        assertTrue(actual, "The list of matches returned by this method should all have the " +
                "same date which was passed as a parameter");
    }

}
