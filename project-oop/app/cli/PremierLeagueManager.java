package cli;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PremierLeagueManager implements LeagueManager, Serializable {

    	private static PremierLeagueManager manager = null;
    	private List<FootballClub> allFootballClubs = new ArrayList<>();
    	private List<Match> allMatches = new ArrayList<>();
    	private static String status;
    	public static final int MAX_CLUBS = 20;
    	public static final int WIN_POINTS = 3;
    	public static final int DRAW_POINTS = 1;
    	public static final int DEFEAT_POINTS = 0;

    	private PremierLeagueManager() {
    	}

    	public static PremierLeagueManager getInstance() {      //ensures that only one instance of PremierLeagueManager will exist

        	if(manager == null) {                               //checks whether manager object has been initialized
            		synchronized (PremierLeagueManager.class) {     //blocks another thread from initializing the manager
                		if(manager == null) {                       //checks whether manager object has been initialized during the passed time
                    			manager = new PremierLeagueManager();   //initializes PremierLeagueManager

                		}
            		}
        	}
        	return manager;
    	}

    	public void setAllFootballClubs(List<FootballClub> allFootballClubs) {
        	this.allFootballClubs = allFootballClubs;
    	}

    	public void setAllMatches(List<Match> allMatches) {
        	this.allMatches = allMatches;
    	}

    	public List<FootballClub> getAllFootballClubs() {
        	return allFootballClubs;
    	}

    	public List<Match> getAllMatches() {
        	return allMatches;
    	}

    	public static String getStatus() {
        	return status;
    	}

    	public static void setStatus(String status) {
        	PremierLeagueManager.status = status;
    	}

    	public static PremierLeagueManager loadDataFromFile(String fileName) {

        	File file = new File(fileName);
        	if (file.length() == 0) {                            //gets manager instance if the file is empty
            		status = "No data has been saved yet...";
            		return getInstance();
        	}

        	try {

            		FileInputStream fileInputStream = new FileInputStream(file);
            		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            		try {                                         //since the file is not empty, manager is initialized by object reading
                		manager = (PremierLeagueManager) objectInputStream.readObject();
                		status = "Premier League Manager has been loaded successfully!";
                		objectInputStream.close();
                		fileInputStream.close();
                		return getInstance();

            		} catch (EOFException | ClassNotFoundException e) {     //in the events of not being able to load file, manager is initialized newly
                		status = "File could not be loaded...";
                		objectInputStream.close();
                		fileInputStream.close();
                		return getInstance();
            		}

        	} catch (IOException e) {
            		status = "File could not be loaded...";
            		return getInstance();
        	}
    	}

    
	@Override
    	public void addClub(SportsClub newClub) {
        	if(newClub instanceof FootballClub) {  //club is added to allFootballClubs once confirmed that it's a FootballClub instance
            		allFootballClubs.add((FootballClub) newClub);
        	}
    	}

    	@Override
    	public void deleteClub(String clubName) {

        	for (FootballClub footballClub: allFootballClubs) {
            		if(footballClub.getName().equals(clubName)) {
                		allFootballClubs.remove(footballClub);      //club is deleted if the parameter matches to an existing club's name
                		break;
            		}
        	}
    	}

    	@Override
    	public FootballClub statsOfClub(String name) {

        	FootballClub footballClub = null;

        	for (FootballClub footballClub1: allFootballClubs) {
            		if(footballClub1.getName().equals(name)) {
                		footballClub = footballClub1;              //if the club's name matches the parameter, that club is returned
            		}
        	}

        	return footballClub;
    	}

    	@Override
    	public void addPlayedMatch(LocalDate date, String clubName1, String clubName2, int score1, int score2) {

        	FootballClub footballClub1 = new FootballClub();
        	FootballClub footballClub2 = new FootballClub();

        	for (FootballClub footballClub: allFootballClubs) {     //checks if the 2 clubs sent as parameters exist

            		if(footballClub.getName().equals(clubName1)) {
                		footballClub1 = footballClub;

            		} else if(footballClub.getName().equals(clubName2)) {
                		footballClub2 = footballClub;
            		}
        	}

        	updateStatsAfterMatch(footballClub1, footballClub2, score1, score2);
        	Match match = new Match(date, footballClub1, footballClub2, score1, score2); //if the 2 clubs exist, match is created and added
        	allMatches.add(match);
    	}

    	@Override
    	public void saveDataToFile(String fileName)  {
    		try {
				FileOutputStream fileOutputStream = new FileOutputStream(fileName);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(this);    //this object is written into the text file
				objectOutputStream.flush();
				objectOutputStream.close();
				fileOutputStream.close();
			} catch (IOException e) {
    			System.out.println("File could not be saved");
			}
        	status = "Premier League Data have been saved successfully!";
    	}

    	@Override
    	public void displayLeagueTable() {

        	sortClubsByPoints();      //clubs are sorted first

        	System.out.println("\nPREMIER LEAGUE TABLE");
        	System.out.println("---------------------------------------------------------------------------------------------");
        	System.out.println("| Position |                     Club | Played | Won | Drawn | Lost | GF | GA | GD | Points |");
        	System.out.println("---------------------------------------------------------------------------------------------");

        	int position = 1;

        	for (FootballClub footballClub: allFootballClubs){

            		String club = footballClub.getName();
            		int played = footballClub.getNoOfMatches();
            		int won = footballClub.getWins();
            		int drawn = footballClub.getDraws();
            		int lost = footballClub.getDefeats();
            		int gf = footballClub.getGoalsScored();
            		int ga = footballClub.getGoalsAgainst();
            		int gd = footballClub.getGoalDifference();
            		int points = footballClub.getPoints();

            		//club stats are printed in tabular form one after the other
            		System.out.printf("%4d%33s%9d%6d%8d%7d%5d%5d%5d%9d%n", position, club, played, won, drawn, lost, gf, ga, gd, points);
            		System.out.println("---------------------------------------------------------------------------------------------");

            		position++;
        	}
    	}

    	public void sortClubsByPoints() {

        	allFootballClubs.sort(Collections.reverseOrder());

        	for(int i=0; i<allFootballClubs.size()-1; i++) {
            		for(int j=0; j<allFootballClubs.size()-1-i; j++) {
                		if (allFootballClubs.get(j).getPoints() == allFootballClubs.get(j+1).getPoints()) {  //checks if points are equal
                    			int gd1 = allFootballClubs.get(j).getGoalDifference();		//takes 2 adjacent clubs' GD at a time
                    			int gd2 = allFootballClubs.get(j+1).getGoalDifference();
                    			if (gd1 < gd2) {                                                    //swaps objects if GD is not in descending order
                        			int index1 = allFootballClubs.indexOf(allFootballClubs.get(j));
                        			int index2 = allFootballClubs.indexOf(allFootballClubs.get(j+1));
                        			Collections.swap(allFootballClubs, index1, index2);
                    			}
                		}
            		}
        	}
    	}

    	public void sortClubsByGoalsScored() {
        	allFootballClubs.sort(new GoalsScoredComparator().reversed());
    	} //sorts in descending order of goals scored

    	public void sortClubsByWins() {
        	allFootballClubs.sort(new WinsComparator().reversed());
   	} //sorts in descending order of goals scored

    	public Match generateMatch() {

        	if(allFootballClubs.size()==0 || allFootballClubs.size()==1) { //to check that the Premier League has at least 2 clubs
            		return null;         //returns if there is less than 2 clubs
        	}

        	FootballClub footballClub1 = null;
        	FootballClub footballClub2 = null;

        	while (footballClub1==null || footballClub2==null || footballClub1==footballClub2) {  //picks 2 random clubs
            		Random rand = new Random();
            		footballClub1 = allFootballClubs.get(rand.nextInt(allFootballClubs.size()));
            		footballClub2 = allFootballClubs.get(rand.nextInt(allFootballClubs.size()));
        	}

        	Random random = new Random();               //generates 2 random scores
        	int score1 = random.nextInt(11);
        	int score2 = random.nextInt(11);

        	LocalDate date = LocalDate.now();         //takes today's date as date of generation

        	Match match = new Match(date, footballClub1, footballClub2, score1, score2);
        	updateStatsAfterMatch(footballClub1, footballClub2, score1, score2);
        	allMatches.add(match);        //adds generated match to ArrayList of allMatches
        	return match;

    	}

    	public ArrayList<Match> allMatchesOnADay(LocalDate date) {
	
        	ArrayList<Match> matchesOfDay = new ArrayList<>();

        	for (Match match : allMatches) {                    //checks if each match was played on the date sent as a parameter

            		if (match.getDatePlayed().equals(date)) {     //if there are matches, they are added to the ArrayList to be returned
                		matchesOfDay.add(match);
            		}
        	}
        	return matchesOfDay;
    	}

    	public void updateStatsAfterMatch(FootballClub footballClub1, FootballClub footballClub2, int score1, int score2) {

        	//updates goals scored and goals against in both involved clubs
        	footballClub1.setGoalsScored(footballClub1.getGoalsScored()+score1);
        	footballClub1.setGoalsAgainst(footballClub1.getGoalsAgainst()+score2);
        	footballClub1.setNoOfMatches(footballClub1.getNoOfMatches()+1);

        	footballClub2.setGoalsScored(footballClub2.getGoalsScored()+score2);
        	footballClub2.setGoalsAgainst(footballClub2.getGoalsAgainst()+score1);
        	footballClub2.setNoOfMatches(footballClub2.getNoOfMatches()+1);

        	//checks the match status(win, loss or draw) of the 2 clubs involved and updates wins, draws and defeats
        	if (score1 > score2) {
            		footballClub1.setWins(footballClub1.getWins()+1);
            		footballClub2.setDefeats(footballClub2.getDefeats()+1);
            		footballClub1.setPoints(footballClub1.getPoints() + WIN_POINTS);
            		footballClub2.setPoints(footballClub2.getPoints() + DEFEAT_POINTS);
        	} else if (score1 == score2) {
            		footballClub1.setDraws(footballClub1.getDraws()+1);
            		footballClub2.setDraws(footballClub2.getDraws()+1);
            		footballClub1.setPoints(footballClub1.getPoints() + DRAW_POINTS);
            		footballClub2.setPoints(footballClub2.getPoints() + DRAW_POINTS);
        	} else {
            		footballClub2.setWins(footballClub2.getWins()+1);
            		footballClub1.setDefeats(footballClub1.getDefeats()+1);
            		footballClub2.setPoints(footballClub2.getPoints() + WIN_POINTS);
            		footballClub1.setPoints(footballClub1.getPoints() + DEFEAT_POINTS);
        	}
    	}

}
