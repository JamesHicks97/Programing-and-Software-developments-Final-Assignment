import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */
/**
	 * @param numbers holds the numbers of the entry
	 * @param numbersOfNumbers is the number of numbers a numbers entry should have
	 */
public class NumbersEntry extends Entry implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private int[] numbers;
    public int numberOfNumbers = 7;
    
    NumbersEntry(int entryId, int billId, int memberId){
    	super(entryId, billId, memberId);
    	
    }
    
    /**
     * uses keyboard to set the numbers of manual entries
     * @param kbd
     */
    public void setNumbers(Scanner kbd) {
    	String numbersInput;
    	int[] uncheckedNumbers=  new int[numberOfNumbers];
    	boolean goOn = false;
    	while (goOn ==false) {
    		System.out.println("Please enter 7 different numbers (from the range 1 to 35) separated by whitespace.");
    		numbersInput = kbd.nextLine();
    		if (areAllNumbers(numbersInput)==true) {
    			// parses input that converts the string into an array of ints
    			uncheckedNumbers = stringToArray(numbersInput); 
    			// Checks whether the input is valid
    			goOn = checkValidNumbers(uncheckedNumbers);
    			//while the input is invalid the user is prompted to try again
			} 
		}
		
		//initializes another array of ints that will hold numbers selected by user
		numbers = new int[uncheckedNumbers.length]; 
		Arrays.sort(uncheckedNumbers); //sorts ints in the array in ascending order
		numbers= uncheckedNumbers;
		
		
		 

    }
    
    /**
     * determines if the input is only numbers
     * @param numbersInput
     * @return a true of false depending on whether all inputs are numbers
     */
	public boolean areAllNumbers(String numbersInput) {
		String[] parts = numbersInput.split(" ");// splits the string into an array of strings each word/digit seperated by
    	//spaces becomes its own string
    	//array is intitialised with the length equal to the length of parts 
    	try {
	    	int[] numbers = new int[parts.length];
	    	// each string is converted from a string into an int
	    	for(int n = 0; n < parts.length; n++) {
	    	   numbers[n] = Integer.parseInt(parts[n]);
	    	}
    	} catch(NumberFormatException e) {
    		System.out.println("Invalid input! Numbers are expected. Please try again!");
    		return false;
    	}
    	return true;
	}
	
	
    public void setNumbers(int[] numbers) {
    	this.numbers = numbers;
    }
	
    
    /**
     * takes unchecked numbers and determines if there are there correct amount of numbers all within the allowed range
     * @param uncheckedNumbers
     * @return true or false
     */
	public boolean checkValidNumbers(int[] uncheckedNumbers) {
	    	
	    	//checks if the input is longer then 7 numbers
	    if (uncheckedNumbers.length>numberOfNumbers) {
	    	System.out.println("Invalid input! More than 7 numbers are provided. Please try again!");
	    	return false;
	    		// check if the input is fewer then 7 numbers
	    } else if (uncheckedNumbers.length<numberOfNumbers) {
	    	System.out.println("Invalid input! Fewer than 7 numbers are provided. Please try again!");
	    	return false;
	    	//checks if there are any doubles in the input
	    } else if (checkForDoubles(uncheckedNumbers)==true){
	    	System.out.println("Invalid input! All numbers must be different!");
	    	return false;
	    		//checks that all the numbers are between 1 and 35
	    } else if (checkInRange(uncheckedNumbers)==false){
	    	System.out.println("Invalid input! All numbers must be in the range from 1 to 35!");
	    	return false;
	    } else {
	    	return true;
	    }
	    	
	  }
	    
	    /**
	     * checks for the same number in the manual entry input. if a double is found true is returned
	     * @param uncheckedNumbers
	     * @return true or false
	     */
	    public boolean checkForDoubles(int[] uncheckedNumbers) {
	    	 
	    	// Goes through each number in the entry and checks its value against every other number.
	    	for (int i= 0; i < uncheckedNumbers.length; i++) {
	    		for (int j= i+1; j< uncheckedNumbers.length;j++) {
	    			if (uncheckedNumbers[i]== uncheckedNumbers[j]) {
	    				return true;
	    			}
	    		}
	    	}
	    	return false;
	    }
	    
	    
	    /**
	     *  checks that each number is in the desired range
	     * @param uncheckedNumbers
	     * @return true or false
	     */
	    public boolean checkInRange(int[] uncheckedNumbers) {
	    	int lowestNum = 1; //represents the lowest number that can be used in a ticket
	    	int highestNum = 35;//represents the highest number that can be used in a ticket
	    	
	    	//checks each number is inside the range if it doesn't false is returned
	    	for (int i= 0; i < uncheckedNumbers.length; i++) {
	    		if (uncheckedNumbers[i] < lowestNum || uncheckedNumbers[i] > highestNum) {
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	    
	    
	    /**
	     *  method that converts a string into an array of numbers 
	     * @param numbersInput
	     * @return an array of ints that represent an entries numbers
	     */
	    public int[] stringToArray(String numbersInput) {
	    	
	    	String[] parts = numbersInput.split(" ");// splits the string into an array of strings each word/digit seperated by
	    	//spaces becomes its own string
	    	//array is intitialised with the length equal to the length of parts 
	    	
	    	int[] numbers = new int[parts.length];
	    	// each string is converted from a string into an int
	    	for(int n = 0; n < parts.length; n++) {
	    	   numbers[n] = Integer.parseInt(parts[n]);
	    	}
	         return numbers;
	    }


		public int[] getNumbers() {
			return numbers;
		}
}
