package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import cli.Match;
import cli.PremierLeagueManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MatchController extends Controller {

    private static final String FILE_PATH = "app/data/PLData.txt";
    private static PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    public MatchController() throws IOException {

    }

    //sends all matches
    public Result allMatches() throws IOException {
        premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
        Collections.sort(premierLeagueManager.getAllMatches());
        List<Match> allMatches = premierLeagueManager.getAllMatches();
        JsonNode allMatchesJson = Json.toJson(allMatches);
        return ok(allMatchesJson).as("application/json");
    }

    //sends created match
    public Result createdMatch() throws IOException {
        premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
        Match match = premierLeagueManager.generateMatch();

        String matchDetails = "| Match generated successfully!   |   " +
                "Date: "+match.getDatePlayed().toString()+"   |   "+
                                match.getSportsClub1().getName()+" "+match.getScore1()+ " - "+
                                match.getScore2()+" "+match.getSportsClub2().getName()+
                                " |";

        premierLeagueManager.saveDataToFile(FILE_PATH);

        JsonNode jsonMatch = Json.toJson(matchDetails);
        return ok(jsonMatch).as("application/json");
    }

    //sends matches played on a particular day
    public Result matchesByDate(String stringDate) {
        premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(stringDate, formatter);
        ArrayList<Match> matches = premierLeagueManager.allMatchesOnADay(localDate);
        JsonNode allMatchesJson = Json.toJson(matches);
        return ok(allMatchesJson).as("application/json");
    }

}
