/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public abstract class Competition implements Serializable{
    /**
	 * Competition object
	 */
	private static final long serialVersionUID = 1L;
	private String name; //competition name
    private int id; //competition identifier
    ArrayList<Entry> allEntries = new ArrayList<Entry>(0);
    ArrayList<Entry> winningEntries = new ArrayList<Entry>(0); // ArrayList that holds all the winning entries
    public int entryId= 1; // the id of the next entry to be made
    public int winningTickets=0; // the number of winning tickets
    public int numberOfNumbers=7;
    
    public abstract void addEntries(int memberId, int billId, int entriesPaid, Scanner kbd, int mode, Competition c);
    
    
    public abstract void drawWinners(int mode, Competition c);
    
    public abstract void printNumbers(int i, Competition c);
    public abstract void entryIdMessage(int i, Competition c);
    public abstract String getType();
    
    
    /**
     * reports on the winners of this competition
     * @param c
     * @param allMembers
     */
    public void report(Competition c,  ArrayList<Member> allMembers) {
    	//sort the winningEntries arrayList in order of ascending EntryId
    	
    	
    	Collections.sort(winningEntries, new Comparator<Entry>() {
    			
    			
    		public int compare(Entry e1, Entry e2) {
    					
    				//sort in ascending order
    				return Integer.valueOf(e1.getEntryId()).compareTo(e2.getEntryId());
    			
    		}
    				//sort in descending order
    			//return obj2.name.compareTo(obj1.name);
    			
    	});	
    	
    	
    	System.out.println("Winning entries:");
    	//prints the details of the winning entries.
    	for (int i=0; i<winningEntries.size(); i++) {
    		String memberName = findName(winningEntries.get(i), allMembers);
    		System.out.printf("Member ID: " +winningEntries.get(i).getMemberId() +", Member Name: " +memberName);
    		entryIdMessage(i, c);
    		System.out.printf(", Prize: %-5d\n" ,winningEntries.get(i).getPrize());
    		printNumbers(i, c);
    		
    	}
	    
    }
    
    /**
     * finds the name of a member
     * @param e
     * @param allMembers
     * @return
     */
    public String findName(Entry e, ArrayList<Member> allMembers) {
    	for (int i=0; i<allMembers.size(); i++) {
    		if (e.getMemberId() == allMembers.get(i).getMemberId()) {
    			return allMembers.get(i).getName();
    		}
    	}
    	return "couldnt find a name";
    }
    
	public void setId(int id) {
		this.id = id;
	}
	    
	public int getId() {
		return id;
	}
	    
	public void setName(String name) {
		this.name = name;
	}
	    
	public String getName() {
	    return name;
	}
	    
	public void setWinningTickets(int winningTickets) {
		this.winningTickets = winningTickets;
	}
	    
	public int getWinningTickets() {
	    return winningTickets;
	}

}