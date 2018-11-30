package edu.ncsu.csc316.security_log.data;

/**
 * Holds the info for a given log entry
 * @author Ryan Alexander
 *
 */
public class LogEntry {
	/** Action time */
	private Timestamp timestamp;
	/** Action performed */
	private String action;
	/** Resource accessed */
	private String resource;
	/** User performing */
	private String user;
	/** Frequency of action */
	private int frequency;
	
	private int hashed;
	
	/**
	 * Holds info for a particular log entry
	 * @param user the user
	 * @param timestamp time of occurrence
	 * @param action the action performed
	 * @param resource the resource accessed
	 */
	public LogEntry(String user, String timestamp, String action, String resource) {
		setUser(user);
		setTimestamp(new Timestamp(timestamp));
		setAction(action);
		setResource(resource);
		setFrequency(0);
	}

	/**
	 * Returns the timestamp
	 * @return timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp
	 * @param timestamp the time
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the action
	 * @return action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action
	 * @param action the action
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Gets the resource
	 * @return resource
	 */
	public String getResource() {
		return resource;
	}

	/**
	 * Sets the resource
	 * @param resource the resource
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}

	/**
	 * Gets the user
	 * @return user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the user
	 * @param user the user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the frequency
	 * @return frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * Increases the frequency by one
	 */
	public void increaseFequency() {
		frequency += 1;
	}

	/**
	 * Sets the frequency
	 * @param frequency new freq
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	/**
	 * Represents entry as a string
	 * @return string representation
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.getAction());
		str.append(" ");
		str.append(this.getResource());
		return str.toString();
	}

	/**
	 * Hashing function
	 * Based on cyclic shift as found in the hashcode slides
	 * @return hash code
	 */
	public int hashCode() {
		if (hashed != 0 )
			return hashed;
		int h = 0;
		for(int i = 0; i < this.toString().length(); i++) {
			h = (h << 5);
			h = h + this.toString().charAt(i);
		}
		this.hashed = h;
		return h;
	}
	
	/**
	 * Determines if this object is equal to another
	 * @param o an object to be compared
	 * @return true if the same, false otherwise
	 */
	public boolean equals(Object o) {
		LogEntry other = (LogEntry)o;
		if(this.getAction().equals(other.getAction()) && this.getResource().equals(other.getResource()))
			return true;
		return false;
	}
	
	/**
	 * Compares two logentries
	 * @param other the other log entry
	 * @return -1 if this is before, zero otherwise
	 */
	public int compareTo(LogEntry other) {
		/**
		StringBuilder compare = new StringBuilder();
		StringBuilder against = new StringBuilder();
		int cheat = 100 - this.getFrequency();
		if (cheat < 10) 
			compare.append("0");
		int cheat2 = 100 - other.getFrequency();
		if (cheat2 < 10) 
			against.append("0");
		compare.append(cheat);
		compare.append(" ");
		compare.append(this.toString());
		against.append(cheat2);
		against.append(" ");
		against.append(other.toString());
		System.out.println(compare.toString() + ":" + against.toString());
		if (compare.toString().compareTo(against.toString()) < 0) {
			return -1;
		} else if (compare.toString().compareTo(against.toString()) > 0) {
			return 1;
		}
		*/
		if (this.getFrequency() == other.getFrequency() && this.toString().compareTo(other.toString()) < 0) {
			return -1;
		} else if (this.getFrequency() > other.getFrequency()) {
			return -1;
		} 
		return 0;
	}
	
}
