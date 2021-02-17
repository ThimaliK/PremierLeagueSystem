package cli;

import java.util.Comparator;

public class WinsComparator implements Comparator<SportsClub> {
	//compares wins property of 2 SportsClub objects
    	@Override
    	public int compare(SportsClub o1, SportsClub o2) {
        	return o1.getWins() - o2.getWins();
    	}
}
