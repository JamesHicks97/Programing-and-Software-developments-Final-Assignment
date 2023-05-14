import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

public class BehindScenes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int yes = 1;
	final int no = 2;
	final int undecided = 0;
	
	/**
	 *  Asks user whether they would like to load an existing competition an accommodates their decision.
	 * @param shouldLoad
	 * @param kbd
	 * @param sc
	 * @return the simplecompetiton that was saved in the read file
	 */
 	public SimpleCompetitions loadComps(boolean shouldLoad, Scanner kbd, SimpleCompetitions sc) {
		
	
			System.out.println("File name:");
			String fileName = kbd.nextLine();
			try {
				File tempFile = new File(fileName);
				if(tempFile.exists()) {
					
						FileInputStream fileIS = new FileInputStream(fileName);
						ObjectInputStream ObjectIs = new ObjectInputStream(fileIS);
						sc = (SimpleCompetitions)ObjectIs.readObject(); 
						ObjectIs.close();
				} else {
					throw new DataAccessException();
				}
			} catch (DataAccessException e) {
				 System.out.println(e.getMessage());
				 System.exit(0);
			} catch (IOException e) {
	 			e.printStackTrace();
	 		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		return sc;
	}
	
 	/**
 	 * gets a yes or no answer from user
 	 * @param kbd
 	 * @param message
 	 * @return true for yes false for no
 	 */
	public boolean yesOrNo(Scanner kbd, String message) {
		boolean done =false;
		
		while (!done) {
			System.out.println(message);
			String response = kbd.nextLine();
			switch (response) {
			
			case "Y":
				return true;
			case "y":
				return true;
			case "N":
				return false;
			case "n":
				return false;
			}
	
		
		System.out.println("Unsupported option. Please try again!");
		
		}
		return false;
	}
	
	/**
	 * gets a choice of 1, 2,3,4 or 5 for the main menu of simple competitions
	 * @param kbd
	 * @return int
	 */
	public int optionChoiceCheck(Scanner kbd) {
		boolean done = false;
		int option= 0;
		while (done== false) {
			System.out.println("Please select an option. Type 5 to exit.");
			System.out.println("1. Create a new competition");
			System.out.println("2. Add new entries");
			System.out.println("3. Draw winners");
			System.out.println("4. Get a summary report");
			System.out.println("5. Exit");
			try {
				
				option = kbd.nextInt();
				if (option > 5||option < 1) {
					System.out.println("Unsupported option! Please try again.");
				} else {
					done = true;
				}
			} catch(InputMismatchException e) {
				System.out.println("A number is expected. Please try again.");
			} finally {
                kbd.nextLine();
			}
		}
		
		return option;
	}	
	
	/**
	 * establishes a new competition
	 * @param kbd
	 * @param c
	 * @param compId
	 */
	public void establishComp(Scanner kbd, Competition c, int compId) {
			
			System.out.println("Competition name: ");
			kbd.nextLine(); // could not find way to avoid this line
			String name =kbd.nextLine();
			c.setName(name); //Uses competition classes setter to set the name of the comp
			c.setId(compId); ////Uses competition classes setter to set the ID of the comp
			System.out.println("A new competition has been created!");
			System.out.println("Competition ID: " +c.getId() +", Competition Name: " +c.getName() +", Type: " +c.getType());
				
	}
	
	/**
	 * checks a valid more has been selected
	 * @param kbd
	 * @return an int (1 represents testing mode 2 normal)
	 */
	public int validModeCheck(Scanner kbd) {
		
		System.out.println("Which mode would you like to run? (Type T for Testing, and N for Normal mode):");
		String mode = kbd.next();
		
		switch (mode) {
		
		case "T":
			return 1;
		case "t":
			return 1;
		case "N":
			return 2;
		case "n":
			return 2;

		}
		
		System.out.println("Invalid mode! Please choose again.");
		return undecided;
		
	}
	
	/**
	 * detetmines whether the entered comp type is valid
	 * @param kbd
	 * @return int 1 for luck 2 for random
	 */
	public int validCompType(Scanner kbd) {
		boolean done = false;
		while (!done) {
			System.out.println("Type of competition (L: LuckyNumbers, R: RandomPick)?:");
			String type = kbd.next();
			
			switch (type) {
			
			case "L":
				return 1;
			case "l":
				return 1;
			case "R":
				return 2;
			case "r":
				return 2;
			}
			System.out.println("Invalid competition type! Please choose again.");
		}
		
		return undecided;
		
	}
	
	/**
	 * whther id is valid or not
	 * @param kbd
	 * @param identifier
	 * @return returns id if valid
	 */
	public int validID(Scanner kbd, String identifier) {
		
		System.out.println(identifier +" ID: ");
		String ID = kbd.next();
		int expectedIdLength= 6;
		//Checks whether the input is the right length
		if (ID.length() == expectedIdLength) {
			//If it's the right length it cycles through each character 
			for (int i = 0; i<ID.length(); i++) {
				//checks the ASCII value of the character  to make sure its a number between 0 and 9 if not 0 is returned indicating an error
				if (Character.getNumericValue(ID.charAt(i)) >9 || Character.getNumericValue(ID.charAt(i)) < 0 ) {
					System.out.println("Invalid " +identifier.toLowerCase() +" id! It must be a 6-digit number. Please try again.");
					return 0;
				}
			}
			//If no invalid character is detected in the string the string is converted to an integer and returned
			int IDint= Integer.parseInt(ID);
			return IDint;
		}
		System.out.println("Invalid " +identifier.toLowerCase() +" id! It must be a 6-digit number. Please try again.");
		kbd.nextLine();
		return 0;
	}
	
	/**
	 * handles the enterring of entries
	 * @param kbd
	 * @param c
	 * @param allBills
	 * @param mode
	 */
	public void handleEntries( Scanner kbd, Competition c, ArrayList<Bill> allBills, int mode) {
		 int entriesPaid = 0; //tracks how many entries have been paid for by the current bill
	     int billMarker; // which element of allBills is the correct bill
	     boolean cont = true; // indicates whether the more entries will be added after a certain bill is finished
	     int ticketCost = 50;
	     while (cont==true ) {
	    	 	
				billMarker = entryCheck(kbd, allBills); 
				entriesPaid = (int) allBills.get(billMarker).getBillTotal()/ticketCost;
				System.out.printf("This bill ($" +allBills.get(billMarker).getBillTotal() +") is eligible for " +entriesPaid +" entries."); 
				
				
					// calls entry constructor
					
					c.addEntries(allBills.get(billMarker).getMemberId(), allBills.get(billMarker).getBillId(), entriesPaid,  kbd, mode, c);
				
				
				cont = yesOrNo(kbd, "Add more entries (Y/N)?");
	     }
	}
	
	
	/**
	 * checks whether an entry is valid
	 * @param kbd
	 * @param allBills
	 * @return
	 */
	public int entryCheck(Scanner kbd, ArrayList<Bill> allBills) {
		
	    int billId =0;
		int i=0;
			
			// asks for a bill id until a valid id is given
			billLoop:
			while (billId == 0) {
				//i serves as a marker for where in the all bills array list the matching bill id is found. It is reset to zero each time a new billid is entered
				i=0;
				billId =validID(kbd, "Bill");
				if (billId == 0) {
					continue billLoop;
				}
				//cycles through all the Bills in allBills array list
				for(Bill currentBill: allBills ) {
					//if there is a Bill with matching the bill it checks whether the bill has a valid member id.
					if(billId == currentBill.getBillId()) {
						if (currentBill.getMemberId() == 0) {
							//if the bill doesn't have a valid member id the user is prompted to try again
							System.out.println("This bill has no member id. Please try again.");
							billId = 0;
							continue billLoop;
						} else if (currentBill.getUsed() == true) {
							System.out.println("This bill has already been used for a competition. Please try again.");
							billId = 0;
							continue billLoop;
						}
						//if the member id is valid the marker to the bills place in allbills is returned
						currentBill.setUsed(true);
						return i;
					}
					//if the current bill does not match the marker updates to the next
					i++;	
				}
				//if no match is found the user is prompted to try again.
				System.out.println("This bill does not exist. Please try again.");
				billId = 0;
			}
		return i;
	}
	
	/**
	 * saves all comps to external file
	 * @param sc
	 * @param kbd
	 */
	public void saveFile(SimpleCompetitions sc, Scanner kbd) {
		System.out.println("File name:");
		String fileName = kbd.nextLine();
		try {
			FileOutputStream fileOs = new FileOutputStream(fileName);
			ObjectOutputStream ObjectOs = new ObjectOutputStream(fileOs);
			ObjectOs.writeObject(sc);
			ObjectOs.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Competitions have been saved to file.");
	}
	/**
	 * overrides billfile
	 * @param billFile
	 * @param allBills
	 */
	public void overRide(String billFile, ArrayList<Bill> allBills) {
		
		try {
			
			FileWriter w = new FileWriter(billFile);
			for(int i = 0; i<allBills.size() ; i++) {
				
				w.append(String.valueOf(allBills.get(i).getBillId()) + ",");
				w.append(String.valueOf(allBills.get(i).getMemberId()) + ",");
				w.append(String.valueOf(allBills.get(i).getBillTotal()) + ",");
				w.append(String.valueOf(allBills.get(i).getUsed()));
				w.append("\n");

			}
			w.close();
			System.out.println("The bill file has also been automatically updated.");
		} catch (Exception e) {
			System.out.print("Couldn't write to file");
		}
	}

}
