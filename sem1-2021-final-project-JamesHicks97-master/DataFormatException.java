import java.io.Serializable;

/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

public class DataFormatException extends Exception implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataFormatException() {
		super("Data was entered in the wrong format. Application terminating.");
	}
	
	public DataFormatException(String message) {
		super(message);
	}
}
