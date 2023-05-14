/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AutoNumbersEntry extends NumbersEntry implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int NUMBER_COUNT = 7;
    private final int MAX_NUMBER = 35;
	
    public int[] createNumbers (int seed) {
        ArrayList<Integer> validList = new ArrayList<Integer>();
	int[] tempNumbers = new int[NUMBER_COUNT];
        for (int i = 1; i <= MAX_NUMBER; i++) {
    	    validList.add(i);
        }
        Collections.shuffle(validList, new Random(seed));
        for (int i = 0; i < NUMBER_COUNT; i++) {
    	    tempNumbers[i] = validList.get(i);
        }
        Arrays.sort(tempNumbers);
        return tempNumbers;
    }
    
 // random number generator without seed
    public int[] createNumbers () {
        ArrayList<Integer> validList = new ArrayList<Integer>();
	int[] tempNumbers = new int[7];
        for (int i = 1; i <= 35; i++) {
    	    validList.add(i);
        }
        Collections.shuffle(validList, new Random());
        for (int i = 0; i < 7; i++) {
    	    tempNumbers[i] = validList.get(i);
        }
        Arrays.sort(tempNumbers);
        return tempNumbers;
    }

    
    AutoNumbersEntry(int entryId, int billId, int memberId, int mode) {
		
		super(entryId, billId, memberId);
		//determine how random numbers will be generated depending on mode
		switch (mode) {
		// if testing mode a seed is given
		case 1:
			setNumbers(createNumbers(entryId-1));
			
			break;
		// if in normal mode no seed is given
		case 2:
			setNumbers(createNumbers());
			
		break;
		}
		setIdentifier(" [Auto]");
	}
}
