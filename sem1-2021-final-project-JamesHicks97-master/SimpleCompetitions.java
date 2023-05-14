/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
/**
	 * @param allCompetitions holds all created competitions
	 * @param allBills holds the supplied bills
	 * @param allMembers holds the members
	 * @param active conveys whether a comp is currently active
	 * @param compId holds the id of the current comp
	 * @param mode holds what type the app is operating on (normal or testing
	 */
public class SimpleCompetitions implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
	ArrayList<Competition> allCompetitions = new ArrayList<Competition>(0); 
	ArrayList<Bill> allBills = new ArrayList<Bill>(0);
	ArrayList<Member> allMembers = new ArrayList<Member>(0);
	public boolean active = false; 
	public int compId =1;
	public int mode = 0;
	
   
    /**
     * Prints a summary report of all competitions
     * @param allCompetitions 
     * @param active
     * @param activeComps tracks if there is an active comp or not
     * @param complertedComps how many comps have been completed
     * @totalWinnings totals the prizes given in each comp
     */

    public void report(ArrayList<Competition> allCompetitions, boolean active) {
    	
    	int activeComps=0; // Used when calculating the number of completed comps
    	int completedComps; // How many comps have been completed
    	 // Tracks the total prizes paid out in a comp
    	int totalWinnings;
    	System.out.println("----SUMMARY REPORT----");
    	//computes the number of active and completed comps 
    	if (active == true) {
    		activeComps++; 
    		completedComps = allCompetitions.size()-1;
    	} else {
    		completedComps = allCompetitions.size();
    	}
    	
    	System.out.println("+Number of completed competitions: " +completedComps);
    	System.out.println("+Number of active competitions: " +activeComps);
    	
    	for (int i=0; i < allCompetitions.size(); i++) {
    		totalWinnings=0;
    		System.out.print("\nCompetition ID: " +allCompetitions.get(i).getId());
    		System.out.print(", name: " +allCompetitions.get(i).getName());
    		//when it gets to the last competition check whether its active
    		if (i == allCompetitions.size()-1 && active == true) {
    			System.out.println(", active: yes");
    		} else {
    			System.out.println(", active: no");
    		}
    		System.out.println("Number of entries: " +allCompetitions.get(i).allEntries.size());
    		// Checks whether the competition being checked has any winners
    		if (allCompetitions.get(i).winningEntries.size() > 0) {
    			System.out.println("Number of winning entries: " +allCompetitions.get(i).winningEntries.size());
    			//if there are winning entries each winning entries winnings is added to the total winnings of that comp
    			for (int j=0; j< allCompetitions.get(i).winningEntries.size(); j++) {
    				totalWinnings+=allCompetitions.get(i).winningEntries.get(j).getPrize();
    			}
    			System.out.println("Total awarded prizes: " +totalWinnings);
    		}
    	}
    }

    /**
    * Main program that uses the main SimpleCompetitions class
    * @param invalid  represents invalid inputs
    * @param optionChoice represents choice made by user in main menu
    * @param save represents whether the comps should be saved
    * @param  memberFile, billFile paths to their respective files
    * @param compType represents the type for a comp 
    * @param lucky and random pertain to comp types
    * @param kbd is System.in scanner
    * @param sc is the Simple competitions object
    * @param bs is behind the scenes object that handles alot of IO
    * @param should load represents whether existing comps should be loaded from a file
    */
    public static void main(String[] args) {
    	final int  invalid = 0;
    	
    	int optionChoice= invalid; 
    	boolean save;
    	String memberFile, billFile;
    	int compType;
    	
    	Scanner kbd = new Scanner(System.in);
    	final int lucky = 1;
    	final int random = 2;
    	//Create object to handle setup input/output
    	BehindScenes bs = new BehindScenes();
    	//Create an object of the SimpleCompetitions class
        SimpleCompetitions sc = new SimpleCompetitions();
        
       
        
        System.out.println("----WELCOME TO SIMPLE COMPETITIONS APP----");
		boolean shouldLoad = bs.yesOrNo(kbd, "Load competitions from file? (Y/N)?");
        if (shouldLoad == true) {
        	sc = bs.loadComps(shouldLoad, kbd, sc);
        } else {
        	while (sc.mode == invalid) {
        		sc.mode = bs.validModeCheck(kbd);
        	}
        	
        }
        System.out.println("Member file: ");
        memberFile = kbd.next();
        
        System.out.println("Bill file: ");
        billFile = kbd.next();
        
		DataProvider dp = new DataProvider(memberFile, billFile, sc.allMembers, sc.allBills);
		
        // Keeps asking the user for a choice of what to do in simple competitions until a valid choice is valid
       
        optionChoice = bs.optionChoiceCheck(kbd);
        
        while (optionChoice != 5) {
        	
        	switch (optionChoice) {
        	
        	case 1:
 				// If the user selects 1 to create a new competition it checks whether there is already an active comp
 				//if there is not it goes through the process of creating one. 
 				if (sc.getActive() == false ) {
 					
 					compType =bs.validCompType(kbd);
 					switch (compType) {
 					case lucky:
 						
 						LuckyNumbersCompetition lc = new LuckyNumbersCompetition();
 						sc.allCompetitions.add(lc); // Calls the method that calls a constructor of a competition.
 						bs.establishComp(kbd, lc, sc.compId);
 						break;
 					
 					case random:
 						RandomPickCompetition rc = new RandomPickCompetition();
 						sc.allCompetitions.add(rc); // Calls the method that calls a constructor of a competition.
 						bs.establishComp(kbd, rc, sc.compId);
 						break;
 					}
	 				
	 				 // Names the current active comp as c for ease of use.
	 				 // Establishes the comps name and id
	 				sc.setActive(true); // Notifies simple competitions that there is an active comp
	 				sc.compId++; //Updates what the next comp made should be 
	 				optionChoice = bs.optionChoiceCheck(kbd); // returns the user to the option menu
 				} else {
 					// Displays error message if the is already an active comp
 					System.out.println("There is an active competition. SimpleCompetitions does not support concurrent competitions!");
 					optionChoice = bs.optionChoiceCheck(kbd);
 				}
 				break;
 				
        	case 2:
        		if (sc.getActive() == false) {
 					//Displays error message if there is no comp active
 					System.out.println("There is no active competition. Please create one!");
 					optionChoice = bs.optionChoiceCheck(kbd);
 				
 				} else {
 					// If there is an active comp handle entries is called
 					bs.handleEntries(kbd, sc.allCompetitions.get(sc.allCompetitions.size()-1), sc.allBills, sc.mode);	
 					optionChoice = bs.optionChoiceCheck(kbd);
 				}
 				break;
 				
        	case 3:
 				//If 3 is selected to draw winners there is a check to see whether there is an active comp
 				if (sc.getActive() == false) {
 					//Displays error message if there is no comp active
 					System.out.println("There is no active competition. Please create one!");
 					optionChoice = bs.optionChoiceCheck(kbd);
 					// If there is an active competition with no entries an error message is displayed
 				} else if (sc.allCompetitions.get(sc.allCompetitions.size()-1).allEntries.size()==0){
 					System.out.println("The current competition has no entries yet!");
 					optionChoice=bs.optionChoiceCheck(kbd);
 				} else {
 					// If there is an active competitions with entries draw winners is called from the competitions 
 					// object and simple competitions is notified that the competition is now inactive
 					
 					sc.allCompetitions.get(sc.allCompetitions.size()-1).drawWinners(sc.mode, sc.allCompetitions.get(sc.allCompetitions.size()-1));
 					sc.allCompetitions.get(sc.allCompetitions.size()-1).report(sc.allCompetitions.get(sc.allCompetitions.size()-1), sc.allMembers);
 					sc.setActive(false);
 					optionChoice=bs.optionChoiceCheck(kbd);
 				}
 				break;
 				
        	case 4:
 				//If 4 is selected to return a summary report there is a check of whether any comps have been crated yet
 				if (sc.allCompetitions.size()==0) {
 					System.out.println("No competition has been created yet!");
 					optionChoice=bs.optionChoiceCheck(kbd);
 					// if there is at least one competition the report method from simple competitions is called 
 				} else {
 					sc.report(sc.allCompetitions, sc.active);
 					optionChoice=bs.optionChoiceCheck(kbd);
 				}
 				break;
        	}
        }
        
        save = bs.yesOrNo(kbd, "Save competitions to file? (Y/N)?");
        if(save == true) {
        	bs.saveFile(sc, kbd);
        	bs.overRide(billFile, sc.allBills);
        }
        System.out.println("Goodbye!");
        kbd.close();
    }


	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
