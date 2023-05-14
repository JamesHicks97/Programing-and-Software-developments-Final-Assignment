import java.io.Serializable;

/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

public class Bill implements Serializable {
	/**
	 * Bill object
	 */
	private static final long serialVersionUID = 1L;
	public int billId;
	public int memberId;
	public float billTotal;
	public boolean used;
	
	
	Bill(int billId, int memberId, float billTotal, boolean used) {
		this.billId = billId;
		this.memberId = memberId;
		this.billTotal = billTotal;
		this.used = used;
	}
	
	public void setBillId(int billId) {
    	this.billId = billId;
    }
    
    public int getBillId () {
    	return billId;
    }
    
    public void setMemberId(int memberId) {
    	this.memberId = memberId;
    }
    
    public int getMemberId() {
    	return memberId;
    }
    public void setBillTotal(int billTotal) {
    	this.billTotal = billTotal;
    }
    
    public float getBillTotal() {
    	return billTotal;
    }
    
    public void setUsed(boolean used) {
    	this.used = used;
    }
    
    public boolean getUsed() {
    	return used;
    }

	
}
