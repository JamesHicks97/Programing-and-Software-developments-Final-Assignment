import java.io.Serializable;

/*
 * Student name: James Hicks
 * Student ID: 833966
 * LMS username: jhicks2
 */

public class DataAccessException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataAccessException() {
		super("Could not access file");
	}
	
	public DataAccessException(String message) {
		super(message);
	}
}
