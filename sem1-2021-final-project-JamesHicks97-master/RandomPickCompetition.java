import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

public class RandomPickCompetition extends Competition implements Serializable {
    /**
	 * @param []_prize holds payout for respective place
	 * @param prizes holds prize payouts
	 * @param type hold the string that identifies the type of comp
	 * @param MAX_WINNING_ENTIRIES relates to the max amount of winning tickets there can be
	 */
	private static final long serialVersionUID = 1L;
	private final int FIRST_PRIZE = 50000;
    private final int SECOND_PRIZE = 5000;
    private final int THIRD_PRIZE = 1000;
    private final int[] prizes = {FIRST_PRIZE, SECOND_PRIZE, THIRD_PRIZE};
	final String type = "RandomPickCompetition";
    private final int MAX_WINNING_ENTRIES = 3;
    
    /**
     * adds entries to a randompick comp
     * @param memberId
     * @param billId
     * @param entriesPaid
     * @param kbd
     * @param c
     * @param mode
     */
    public void addEntries(int memberId, int billId, int entriesPaid,  Scanner kbd, int mode, Competition c) {
    	
    	for (int i = 0; i < entriesPaid; i++) {
    		
    		Entry e = new Entry(entryId, billId, memberId);
			allEntries.add(e);
			entryId++;
    	}
    	
    	
	    System.out.println("\nThe following entries have been automatically generated:");
        	for (int i = entryId- (entriesPaid+1) ; i < entryId-1; i++) {
        		System.out.printf("Entry ID: %-5d \n", allEntries.get(i).getEntryId());
        	}
        kbd.nextLine();
    }
    
    
    /**
     * picks winners from all entries for random pick comp
     * @param mode
     * @param c
     */
    public void drawWinners(int mode, Competition c) {
        Random randomGenerator = null;
        if (mode == 1) {
            randomGenerator = new Random(c.getId());
        } else {
            randomGenerator = new Random();
        }
        int enteredMembers= howManyMembers(allEntries);
		// tracks how many winning tickets there have been
        int winningEntryCount = 0;
        //makes sure only three tickets win
        findWinLoop:
        while (winningEntryCount < MAX_WINNING_ENTRIES && winningEntryCount < enteredMembers) {
        	//picks an entry randomly from allEntries
            int winningEntryIndex = randomGenerator.nextInt(allEntries.size());
            int currentPrize = prizes[winningEntryCount];
            // defines a new entry equal to that picked
            Entry winningEntry = allEntries.get(winningEntryIndex);
		    
            //Checks to see if the chosen entry is already in the winning tickets. if it is it returns to the top of the outer loop to find another ticket
            for (int i = 0; i < winningEntries.size(); i++) {
            	if (winningEntry.getEntryId() ==winningEntries.get(i).getEntryId()) {
            		continue findWinLoop;
            	} else if(winningEntry.getMemberId() == winningEntries.get(i).getMemberId()) {
            		winningEntryCount++;
            		continue findWinLoop;
            	}
            }
          
            
            
                
            winningEntry.setPrize(currentPrize);
            c.winningEntries.add(winningEntry);
            winningEntryCount++;
           
        }
        System.out.printf("Competition ID: " +(getId())  +", Competition Name: " +c.getName() +", Type: " +c.getType() +"\n");
      
    }
    
    /**
     * 
     * @param allentires
     * @param totalMembers tallies the number of members participatin in comp 
     * @return totalMembers
     */
    public int howManyMembers(ArrayList<Entry> allentires) {
    	int totalMembers =0 ;
    	ArrayList<Integer> enteredMembers = new ArrayList<Integer>(0); 
    	outerLoop:
    	for (int i = 0; i < allEntries.size(); i++) {
    		for(int j=0; j<totalMembers; j++) {
    			
    			if (allEntries.get(i).getMemberId() == enteredMembers.get(j)) {
    				continue outerLoop;
    			} 
    			
    		}	
    		enteredMembers.add(allEntries.get(i).getMemberId());
    		totalMembers++;
    	}
    	return totalMembers;
    }
    public void printNumbers(int i, Competition c) {
    	
    }
    
	public String getType() {
		return type;
	}
	
	 public void entryIdMessage(int i, Competition c) {
		 	
	    	System.out.print(", Entry ID: " +winningEntries.get(i).getEntryId());
	    }
}
