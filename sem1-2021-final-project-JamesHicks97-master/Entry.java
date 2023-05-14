/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */
import java.io.Serializable;
import java.util.Scanner;

public class Entry implements Serializable{
	
    
	/**
	 * Entry object
	 */
	private static final long serialVersionUID = 1L;
	private int entryId;
    private int billId;
    private int memberId;
    public String identifier; // printed after the tickets number either blank or [Auto]
    public int[] numbers;
    public int prize;// the prize this ticket is entitled too
    
    public Entry(int entryId, int billId, int memberId) {
		
		this.entryId = entryId;
		this.billId = billId;
		this.memberId = memberId;
    }
    
    public  int[] getNumbers() {
    	return numbers;
    }
    
	public int getEntryId() {
		return entryId;
	}
	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public void setNumbers(int[] numbers) {
		// TODO Auto-generated method stub
		this.numbers=numbers;
	}
    
	public int getPrize() {
		return prize;
	}
	
	public void setPrize(int prize) {
		this.prize = prize;
	}
    
}
