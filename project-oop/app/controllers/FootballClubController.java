package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import cli.FootballClub;
import cli.PremierLeagueManager;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.util.List;


public class FootballClubController extends Controller {

    private static final String FILE_PATH = "app/data/PLData.txt";
    private static PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    public FootballClubController() throws IOException {
    }

    //gets all football clubs
    public Result allClubs() {
        premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
        premierLeagueManager.sortClubsByPoints();
        List<FootballClub> allClubs = premierLeagueManager.getAllFootballClubs();
        JsonNode allFootballClubsJson = Json.toJson(allClubs);
        return ok(allFootballClubsJson).as("application/json");
    }

    //gets all football clubs sorted by wins
    public Result clubsSortedByWins() {
        premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
        premierLeagueManager.sortClubsByWins();
        List<FootballClub> allClubs = premierLeagueManager.getAllFootballClubs();
        JsonNode allFootballClubsJson = Json.toJson(allClubs);
        return ok(allFootballClubsJson).as("application/json");
    }

    //gets all football clubs sorted by goals scored
    public Result clubsSortedByGF() {
        premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
        premierLeagueManager.sortClubsByGoalsScored();
        List<FootballClub> allClubs = premierLeagueManager.getAllFootballClubs();
        JsonNode allFootballClubsJson = Json.toJson(allClubs);
        return ok(allFootballClubsJson).as("application/json");
    }

}
