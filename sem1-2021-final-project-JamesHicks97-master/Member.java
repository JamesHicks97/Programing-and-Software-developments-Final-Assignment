import java.io.Serializable;

/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

public class Member implements Serializable {
	
	/**
	 * Member object 
	 * @param memberId identifier for the member
	 * @param name
	 * @param address
	 */
	private static final long serialVersionUID = 1L;
	public int memberId;
	public String name;
	public String address;
	
	Member(int memberId, String name, String address) {
		this.memberId = memberId;
		this.name = name;
		this.address = address;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

	
}
