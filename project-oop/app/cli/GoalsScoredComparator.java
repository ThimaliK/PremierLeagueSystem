package cli;

import java.util.Comparator;

public class GoalsScoredComparator implements Comparator<FootballClub> {
	//compares goalsScored property of 2 FootballClub objects
    	@Override
		public int compare(FootballClub o1, FootballClub o2) {
        	return o1.getGoalsScored() - o2.getGoalsScored();
    	}
}
