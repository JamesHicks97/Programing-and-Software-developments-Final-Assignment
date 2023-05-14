import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

public class LuckyNumbersCompetition extends Competition implements Serializable {
	
	/**
	 * child class of competition
	 */
	private static final long serialVersionUID = 1L;
	final String type = "LuckyNumbersCompetition";
	/**
	 * add entries to lucky numbers comps
	 * @param memberId, billID
	 * 
	 */
	public void addEntries(int memberId, int billId, int entriesPaid, Scanner kbd, int mode, Competition c) {
    	
    	int numberOfNumbers = 7;// number of numbers in each entry ticket 
    	String numbersInput; // captures the numbers input for manual entries
    	
    	int entriesFilled=0;
		boolean done = false;
		System.out.println(" How many manual entries did the customer fill up?: ");
		while (done== false) {
			try {
				
				entriesFilled = kbd.nextInt();
					if(entriesFilled > entriesPaid || entriesFilled < 0) {
						System.out.println("The number must be in the range from 0 to " +entriesPaid +". Please try again.");
					}	else {
						done = true;
					}
			} catch(InputMismatchException e) {
				System.out.println("Invalid input! Numbers are expected. Please try again!");
				kbd.nextLine();
			}
		}
		kbd.nextLine();
    	//Loops for each entry that has been paid for
    	for (int i = 0; i < entriesPaid; i++) {
    		// if all the manual entries have been filled  		
    		if (i< entriesFilled) {
    			
    			NumbersEntry e = new NumbersEntry(entryId, billId, memberId);
    			e.setNumbers(kbd);
    			allEntries.add(e);
    			 //if all the manual entries have been entered an AutoEntry is made
    		} else {
    			int[] numbers = new int[numberOfNumbers];
    			AutoNumbersEntry e = new AutoNumbersEntry(entryId, billId,  memberId, mode);
    			allEntries.add(e);
    		}
    		
    		entryId++;//updates what the next entry id should be
    	}
    	
    	//prints the information of the entries that were added in the last batch
    	System.out.println("The following entries have been added:");
    	for (int i = entryId- (entriesPaid+1) ; i < entryId-1; i++) {
    		System.out.printf("Entry ID: %-2d"  +"\t Numbers:" ,allEntries.get(i).getEntryId());
    		for (int j = 0; j < 7; j++) {
    			 System.out.printf(" %2d"  ,allEntries.get(i).getNumbers()[j]);
    		}
    		if (allEntries.get(i) instanceof AutoNumbersEntry ) { 
    		System.out.println(" [Auto]");
    		} else {
    			System.out.println("");
    		}
    	}
	}
    
	/**
	 * draws winners for lucky comp
	 */
	public void drawWinners(int mode,  Competition c) {
		int[] numbers = new int[numberOfNumbers];
    	int luckyId = 1; //token id of lucky entry
    	int luckyBill = 1; //token bill id
    	//create new auto entry for the lucky entry
    	Entry luckyEntry = new AutoNumbersEntry(getId()+1, luckyBill, luckyId, mode);
    	System.out.printf("Competition ID: " +(getId())  +", Competition Name: " +c.getName() +", Type: " +c.getType() +"\n");
    	System.out.print("Lucky Numbers: ");
    	//prints the lucky entry numbers
    	for (int i = 0; i < 7; i++) {
			 System.out.printf("%2d " ,luckyEntry.getNumbers()[i]);
		}
    	System.out.println("[Auto]");
    	
    	checkWinners(luckyEntry, c);// calls the check winners function
    	
		
	}

	public String getType() {
		return type;
	}
	
	/**
	 * checks all entries is they win a prize
	 * @param luckyEntry
	 * @param c
	 */
	public void checkWinners(Entry luckyEntry, Competition c) {
    	int matchingNumbers; //tracks the number of matching number between a given entry and the lucky entry
    	
    	// Checks all entries for the competition
    	for (int i = 0; i < allEntries.size(); i++) {
    		matchingNumbers = 0;
    		
    		//Checks each number of an entry against each number in the lucky entry and keeps track of 
    		//how many numbers match
    		for (int j = 0; j < 7; j++) {
    			for (int k = 0; k < 7; k++) {
    				if (allEntries.get(i).getNumbers()[j] == luckyEntry.getNumbers()[k]) {
    					matchingNumbers++;
    				}
    			}
    		}
    		checkWinner(matchingNumbers, i, c);// checks whether the current entry is a winner
    		
    	}
    }
    
    /**
     *  checks whether the current entry is a winner
     * @param matchingNumbers
     * @param count
     * @param c
     */
    public void checkWinner(int matchingNumbers, int count, Competition c) {
    	
    	int winnings=0; //represents the prize given for this ticket
    	//prize is detemined by the number of matching numbers
    	switch (matchingNumbers) {
	    	
	    	case 2:
	    		winnings= 50;
	    		break;
	    		
	    	case 3:
	    		winnings= 100;
	    		break;
	    		
	    	case 4:
	    		winnings= 500;
	    		break;
	    		
	    	case 5:
	    		winnings= 1000;
	    		break;
	    		
	    	case 6:
	    		winnings= 5000;
	    		break;
	    		
	    	case 7:
	    		winnings= 50000;
	    		break;
    	}
		
		//checks that the ticket wins a prize
		if (winnings > 0) {
			//checks whether there are already any winning tickets if not the ticket is added to the winning
			//ticket array list with its winnings updated
			if (winningEntries.size()==0) {
				winningEntries.add(allEntries.get(count));
				winningEntries.get(0).setPrize(winnings);
				
				} else {
				//If there is already at least one winning ticket it checks all winning tickets
				for (int i= 0; i< winningEntries.size(); i++) {
					//If the ticket has the same member id as a winning ticket AND has a greater prize then 
					// it replaces the current winning ticket
					if(allEntries.get(count).getMemberId() == winningEntries.get(i).getMemberId()) {
						if (winnings > winningEntries.get(i).getPrize()) {
							winningEntries.get(i).setEntryId(allEntries.get(count).getEntryId());
							winningEntries.get(i).setNumbers(allEntries.get(count).getNumbers());
							winningEntries.get(i).setMemberId(allEntries.get(count).getMemberId());
							winningEntries.get(i).setPrize(winnings);
							winningEntries.get(i).setIdentifier(allEntries.get(count).getIdentifier());
						}
						break;
					// if this ticket belongs to a member that does not have a winning ticket yet the ticket is 
					//added to the winnningEntries ArrayList
					} else if (i==winningEntries.size()-1) {	
						winningEntries.add(allEntries.get(count));
						winningEntries.get(winningEntries.size()-1).setPrize(winnings);
						
					}
				}
			
			} 
			
		}
		
	}
    
    /**
     * Prints the numbers for an entry
     */
    public void printNumbers(int i, Competition c) {
    	System.out.printf("--> Entry ID: " +winningEntries.get(i).getEntryId() 
				+", Numbers:" );
		for (int j=0; j < 7;j++ ) {
			System.out.printf(" %2d"  ,winningEntries.get(i).getNumbers()[j]);
		}
		System.out.println(winningEntries.get(i).getIdentifier());
    }
    
    public void entryIdMessage(int i, Competition c) {
    	
    }
}
