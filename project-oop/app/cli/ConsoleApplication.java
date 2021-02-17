package cli;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ConsoleApplication {

    	private static final String FILE_PATH = "../data/PLData.txt";
    	private static PremierLeagueManager premierLeagueManager = PremierLeagueManager.getInstance();

    	public static void main(String[] args) {

        	premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
        	System.out.println(PremierLeagueManager.getStatus());

        	System.out.println("\n---------------------------------");
        	System.out.println("          PREMIER LEAGUE         |");
        	System.out.println("---------------------------------");

        	menuLoop:
        		while (true) {
            		System.out.println("\nMenu-----------------------------");
            		System.out.println("1: Add a club                    |");
            		System.out.println("2: Delete a club                 |");
            		System.out.println("3: Display statistics of a club  |");
            		System.out.println("4: Add a played match            |");
            		System.out.println("5: Display Premier League Table  |");
            		System.out.println("6: Quit Application              |");
            		System.out.println("---------------------------------");

            		int userInput;
            		Scanner sc = new Scanner(System.in);

            		try {
                		System.out.print("\nEnter option: ");
                		userInput = sc.nextInt();
                		switch (userInput) {
                    			case 1:
                        			premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
                        			addClub();
                        			premierLeagueManager.saveDataToFile(FILE_PATH);
                        			break;
                    			case 2:
                        			premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
                        			deleteClub();
                        			premierLeagueManager.saveDataToFile(FILE_PATH);
                        			break;
                    			case 3:
                        			premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
                        			displayStats();
                        			break;
                    			case 4:
                        			premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
                        			addPlayedMatch();
                        			premierLeagueManager.saveDataToFile(FILE_PATH);
                        			break;
                    			case 5:
                        			premierLeagueManager = PremierLeagueManager.loadDataFromFile(FILE_PATH);
                        			if(isEmpty()) {
                            				System.out.println(PremierLeagueManager.getStatus());
                            				break;
                        			}
                        			premierLeagueManager.displayLeagueTable();
                        			break;
                    			case 6:
                        			premierLeagueManager.saveDataToFile(FILE_PATH);
                        			System.out.println(PremierLeagueManager.getStatus());
                        			System.out.println("Program is exiting...");
                        			break menuLoop;
                    			default:
                        			System.out.println("Input is invalid!!! Please pick one of the menu options...");
                		}

            		} catch (InputMismatchException e) {
                		System.out.print("Invalid input!!! Please enter a menu option...");
            		}
        	}
    	}

    	private static void addClub() {

    		//checks if the club limit is reached
        	if(premierLeagueManager.getAllFootballClubs().size()== PremierLeagueManager.MAX_CLUBS) {
            		System.out.println(PremierLeagueManager.MAX_CLUBS +" clubs have already been added to the Premier League...");
            		return;
        	}

        	//checks if club already exists
        	String name = getClubName();
        	if(clubExistsInPL(name)) {
            		System.out.println("Football Club \""+ name + "\" is already part of the Premier League...");
            		return;
        	}

        	Scanner sc = new Scanner(System.in);
        	System.out.print("Enter the Location of Club: ");
        	String location = sc.nextLine().toLowerCase().trim();

        	FootballClub footballClub = new FootballClub(name, location);
        	premierLeagueManager.addClub(footballClub);
        	System.out.println("Football Club \""+ footballClub.getName() +"\" was successfully added to the Premier League!");

    	}

    	private static void deleteClub() {

    		//checks if there are any clubs to delete
        	if(isEmpty()) {
            		System.out.println("No clubs have been added yet...");
            		return;
        	}

        	String name = getClubName();

        	//checks if the input club exists
        	if(!clubExistsInPL(name)) {
            		System.out.println("Football club \""+name+"\" is not part of the Premier League...");
            		return;
        	}

        	for (FootballClub footballClub: premierLeagueManager.getAllFootballClubs()) {
            		if(footballClub.getName().equals(name)) {
                		break;
            		}
        	}

        	Scanner sc = new Scanner(System.in);      //asks for confirmation from user before deleting
        	System.out.print("Are you sure you want to permanently delete club \""+name+"\" from the Premier League? (y/n) ");
        	String confirmation = sc.nextLine().trim().toLowerCase();

        	if (!confirmation.equals("y")) {
            		return;
        	}

        	premierLeagueManager.deleteClub(name);
        	System.out.println("Football Club \""+name+"\" was successfully deleted from the Premier League!");
    	}

    	private static void displayStats() {

    		//checks if there are any clubs to display
        	if(isEmpty()) {
            		System.out.println("No clubs have been added yet...");
            		return;
        	}

        	//checks if input club exists
        	String name = getClubName();
        	if(!clubExistsInPL(name)) {
            		System.out.println("Football club \""+name+"\" is not part of the Premier League...");
            		return;
        	}

        	FootballClub footballClub;
        	footballClub = premierLeagueManager.statsOfClub(name);

        	if (footballClub==null) {
            		System.out.println("Club \"" +name+ "\" is not part of the Premier League...");
            	return;
        	}

        	System.out.println("\nStatistics of " + footballClub.getName() + "-----------");
        	System.out.println("Played - " + footballClub.getNoOfMatches());
        	System.out.println("Wins - " + footballClub.getWins());
        	System.out.println("Draws - " + footballClub.getDraws());
        	System.out.println("Defeats - " + footballClub.getDefeats());
        	System.out.println("GF - "+ footballClub.getGoalsScored());
        	System.out.println("GA - "+footballClub.getGoalsAgainst());
        	System.out.println("GD - "+footballClub.getGoalDifference());
        	System.out.println("Points - "+ footballClub.getPoints());
        	System.out.println("---------------------------------");
	}

    	private static void addPlayedMatch() {

    		//checks if there are any clubs to add match
        	if(isEmpty()) {
            		System.out.println("No clubs have been added yet...");
            		return;
        	}

        	//checks if there is a minimum of 2 clubs so that a match can be added
        	if(premierLeagueManager.getAllFootballClubs().size()==1) {
            		System.out.println("Only one club has been added, therefore a match cannot be added yet...");
            	return;
        	}

        	Scanner sc = new Scanner(System.in);

        	System.out.println("\nDate-----------------------------");
        	System.out.print("Enter the year: ");
        	String stringYear = sc.nextLine().trim();

        	//checks if the year is valid
        	if (!isValidDateElement(stringYear) || stringYear.length()!=4) {
            		System.out.println("Invalid year! Please try again...");
            		return;
        	}

        	System.out.print("Enter the month: ");
        	String stringMonth = sc.nextLine().trim();
        	if(stringMonth.length()==1) {
            		stringMonth = toDateForm(stringMonth);
        	}

        	//checks if the month is valid
        	if (!isValidDateElement(stringMonth)) {
            		System.out.println("Invalid month! Please try again...");
            		return;
        	}

        	System.out.print("Enter the day: ");
        	String stringDay = sc.nextLine().trim();
        	if(stringDay.length()==1) {
            		stringDay = toDateForm(stringDay);
        	}

			//checks if the day is valid
        	if (!isValidDateElement(stringDay)) {
            		System.out.println("Invalid day! Please enter a correct year...");
            		return;
        	}

        	String stringDate = stringDay+"/"+stringMonth+"/"+stringYear;
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        	try {
        			//makes a LocalDate object with the user's input
            		LocalDate date = LocalDate.parse(stringDate, formatter);
            		System.out.println("\nClub 1---------------------------");
            		String club1 = getClubName();

            		//checks if entered club 1 exists
            		while(!clubExistsInPL(club1)){
                		System.out.println("\""+club1+"\""+" does not exist in the Premier League! Please enter the correct club name...");
                		club1 = getClubName();
            		}

            		try {

                		int score1 = getScoreInput();

                		System.out.println("\nClub 2---------------------------");
                		String club2 = getClubName();

						//checks if entered club 2 exists
                		while (!clubExistsInPL(club2)) {
                    			System.out.println("\""+club2+"\""+" does not exist in the Premier League! Please enter the correct club name...");
                    			club2 = getClubName();
                		}

                		int score2 = getScoreInput();

                		premierLeagueManager.addPlayedMatch(date, club1, club2, score1, score2);
                		System.out.println("Match was successfully added!");
                		System.out.println("---------------------------------");

            		} catch(InputMismatchException e) {  //catches if the entered score is not an integer
                		System.out.println("Invalid score! The score must be an integer. Please try again...");
            		}

        	} catch (DateTimeParseException e) {
            		System.out.println("Invalid date input! Please try again...");
        	}

    	}

    	//gets the club name user input
    	public static String getClubName() {
        	Scanner sc = new Scanner(System.in);
        	System.out.print("Enter Club Name: ");
        	return sc.nextLine().toLowerCase().trim();
    	}

    	//checks if a club exists in the premier league or not
    	public static Boolean clubExistsInPL(String clubName) {   //checks if a particular club exists, given the name
        	boolean clubExists = false;
        	for(FootballClub footballClub: premierLeagueManager.getAllFootballClubs()) {
            		if (footballClub.getName().equals(clubName)) {
                		clubExists = true;
                		break;
            		}
        	}
        	return clubExists;
    	}

    	//checks if the premier league contains at least one club
    	public static Boolean isEmpty() {
        	boolean isPLEmpty = false;
        	if(premierLeagueManager.getAllFootballClubs().size()==0) {
            		isPLEmpty = true;
        	}
        	return isPLEmpty;
    	}

	//checks if user input of day, month or year only contains numbers
    	public static Boolean isValidDateElement(String dateElement) {
        	boolean isValid = false;
        	String regex = "[0-9]+";
        	if (dateElement.matches(regex)) {
            		isValid = true;
        	}
        	return isValid;
    	}

    	//checks if the score inputs are positive integers
		public static int getScoreInput() {
			Scanner sc = new Scanner(System.in);
        	System.out.print("Enter the Score: ");
        	int score = sc.nextInt();
        	while(score<0) {
            		System.out.println("The entered score is invalid! Please enter the correct score...");
            		score = sc.nextInt();
        	}
        	return score;
    	}

    	//in the case of user not entering zero for single digit month or day
    	public static String toDateForm(String dayOrMonth) {
        	return "0"+dayOrMonth;
    	}
}


