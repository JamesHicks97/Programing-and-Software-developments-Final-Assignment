/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */
import java.io. * ;
import java.util.ArrayList;

public class DataProvider implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     * @param memberFile A path to the member file (e.g., members.csv)
     * @param billFile A path to the bill file (e.g., bills.csv)
     * @throws DataAccessException If a file cannot be opened/read
     * @throws DataFormatException If the format of the the content is incorrect
     */
     public DataProvider(String memberFile, String billFile, ArrayList<Member> allMembers, ArrayList<Bill> allBills) {
                  // throws DataAccessException, DataFormatException //{
    	
    	
 		String line = "";
 		
    	try {
    		File tempMF = new File(memberFile);
    		File tempBF = new File (billFile); 
    		
 		if (tempMF.exists() && tempBF.exists()){
 			
 			BufferedReader brBills = new BufferedReader(new FileReader(billFile));
    		BufferedReader brMembers = new BufferedReader(new FileReader(memberFile));
 			while ((line = brBills.readLine()) != null) {
 				 
 				String[] values  = line.split(",");
 				
 					 
 					 int billId=0, memberId;
 					 float billTotal;
 					 boolean active;
 					 if (validID(values[0]) == -1 || validID(values[1]) == -1) {
 						 System.out.println();
 						
 					 } else {
 						 billId= validID(values[0]);
 						 memberId=validID(values[1]);
 						 billTotal= Float.parseFloat(values[2]);
 						 active= Boolean.valueOf(values[3]);
 						 allBills.add(addNewBill(billId, memberId, billTotal, active));
 					 }
 					 
 				 
 			 }
 			brBills.close();
 			while ((line = brMembers.readLine()) != null) {
				 
				 String[] values  = line.split(",");
				 try {
					 
					 int  memberId;
					 String name, address;
					 
					 if (validID(values[0]) == -1) {
						 throw new DataFormatException();
					 } else {
						 
						 memberId=validID(values[0]);
						 name = values[1];
						 address = values[2];
						 allMembers.add(addNewMember(memberId, name, address));
					 }
					 
				 } catch(DataFormatException e) {
					 System.out.println(e.getMessage());
					 System.exit(0);
				 }
				 
			}
 			brMembers.close();
 		} else {
 			throw new DataAccessException();
 		}
 		
 		
    	
 		
 		} catch (DataAccessException e) {
 			 System.out.println(e.getMessage());
				 System.exit(0);
 		} catch (IOException e) {
 			System.exit(0);
 		}
    	
     }
     
     public static int validID( String id) {
 		
 		int empty = 0;
 		int invalid= -1;
 		int expectedIdLength= 6;
 		//Checks whether the input is the right length
 		if (id.length() == empty || id == "0") {
 			return empty;
 		}
 		if (id.length() == expectedIdLength) {
 			//If it's the right length it cycles through each character 
 			for (int i = 0; i<id.length(); i++) {
 				//checks the ASCII value of the character  to make sure its a number between 0 and 9 if not 0 is returned indicating an error
 				if (Character.getNumericValue(id.charAt(i)) >9 || Character.getNumericValue(id.charAt(i)) < 0 ) {
 					
 					return empty;
 				}
 			}
 			//If no invalid character is detected in the string the string is converted to an integer and returned
 			int IDint= Integer.parseInt(id);
 			return IDint;
 		}
 		
 		return empty;
 	}
     
     public Member addNewMember(int memberId, String name, String address) {
     	Member newMember = new Member(memberId, name, address);
     	return newMember;
     }
     
     public Bill addNewBill(int billId, int memberId, float billTotal, boolean active) {
     	Bill newBill = new Bill(billId, memberId, billTotal, active);
     	return newBill;
     }
}
