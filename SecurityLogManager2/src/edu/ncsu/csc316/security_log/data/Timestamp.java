package edu.ncsu.csc316.security_log.data;

/**
 * Holds info for the time of a log
 * @author Ryan Alexander
 *
 */
public class Timestamp {
	/** The month */
	private int month;
	/** The day */
	private int day;
	/** The year */
	private int year;
	/** The hour */
	private int hour;
	/** The minute */
	private int minute;
	/** The second */
	private int second;
	/** AM or PM */
	private String suffix;
	/** Initial string representation */
	private String str;
	
	/**
	 * Constructs the timestamp given a string
	 * @param stamp string representation
	 */
	public Timestamp(String stamp) {
		this.setString(stamp);
		String tmpmonth = "";
		String tmpday = "";
		String tmpyear = "";
		String tmphour = "";
		String tmpminute = "";
		String tmpsecond = "";
		String tmpsuffix = "";
		if (stamp.charAt(1) == '/' && stamp.charAt(11) == ':') {
			tmpmonth = "0" + stamp.charAt(0);
			tmpday = stamp.substring(2, 4);
			tmpyear = stamp.substring(5, 9);
			tmphour = "0" + stamp.charAt(10);
			tmpminute = stamp.substring(12, 14);
			tmpsecond = stamp.substring(15, 17);
			tmpsuffix = stamp.substring(17, 19);
		} else if (stamp.charAt(1) == '/') {
			tmpmonth = "0" + stamp.charAt(0);
			tmpday = stamp.substring(2, 4);
			tmpyear = stamp.substring(5, 9);
			tmphour = stamp.substring(10, 12);
			tmpminute = stamp.substring(13, 15);
			tmpsecond = stamp.substring(16, 18);
			tmpsuffix = stamp.substring(18, 20);
		} else if (stamp.charAt(12) == ':') {
			tmpmonth = stamp.substring(0, 2);
			tmpday = stamp.substring(3, 5);
			tmpyear = stamp.substring(6, 10);
			tmphour = "0" + stamp.charAt(11);
			tmpminute = stamp.substring(13, 15);
			tmpsecond = stamp.substring(16, 18);
			tmpsuffix = stamp.substring(18, 20);
		} else {
			tmpmonth = stamp.substring(0, 2);
			tmpday = stamp.substring(3, 5);
			tmpyear = stamp.substring(6, 10);
			tmphour = stamp.substring(11, 13);
			tmpminute = stamp.substring(14, 16);
			tmpsecond = stamp.substring(17, 19);
			tmpsuffix = stamp.substring(19, 21);
		}
		
		setMonth(Integer.parseInt(tmpmonth));
		setDay(Integer.parseInt(tmpday));
		setYear(Integer.parseInt(tmpyear));
		setHour(Integer.parseInt(tmphour));
		setMinute(Integer.parseInt(tmpminute));
		setSecond(Integer.parseInt(tmpsecond));
		setSuffix(tmpsuffix);
		if (this.getHour() == 12)
			setHour(0);
	}

	/**
	 * Returns the month
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Sets the month
	 * @param month the month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * Gets the day
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Sets the day
	 * @param day the day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Gets the year
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year
	 * @param year the year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Gets the hour
	 * @return hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Sets the hour
	 * @param hour the hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 * Gets the minute
	 * @return minute
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Sets the minute
	 * @param minute the minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 * Gets the second
	 * @return second
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Sets the second
	 * @param second the second
	 */
	public void setSecond(int second) {
		this.second = second;
	}

	/**
	 * Gets AM or PM
	 * @return suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * Sets AM or PM
	 * @param suffix the suffix
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	/**
	 * Compares two timestamps based on occurence
	 * @param timestamp the other time
	 * @return -1 if the this occurred first, 0 if same, 1 otherwise
	 */
	public int compareTo(Timestamp timestamp) {
		if (this.getYear() < timestamp.getYear()) {
			return -1;
		} else if (this.getYear() > timestamp.getYear()) {
			return 1;
		} else if (this.getMonth() < timestamp.getMonth()) {
			return -1;
		} else if (this.getMonth() > timestamp.getMonth()) {
			return 1;
		} else if (this.getDay() < timestamp.getDay()) {
			return -1;
		} else if (this.getDay() > timestamp.getDay()) {
			return 1;
		} else if (this.getSuffix().equals("AM") && timestamp.getSuffix().equals("PM")) {
			return -1;
		} else if (this.getSuffix().equals("PM") && timestamp.getSuffix().equals("AM")) {
			return 1;
		} else if (this.getHour() < timestamp.getHour()) {
			return -1;
		} else if (this.getHour() > timestamp.getHour()) {
			return 1;
		} else if (this.getMinute() < timestamp.getMinute()) {
			return -1;
		} else if (this.getMinute() > timestamp.getMinute()) {
			return 1;
		} else if (this.getSecond() < timestamp.getSecond()) {
			return -1;
		} else if (this.getSecond() > timestamp.getSecond()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Returns a string representation
	 * @return str the string
	 */
	public String toString() {
		return str;
	}

	/**
	 * Sets the string
	 * @param str the string
	 */
	public void setString(String str) {
		this.str = str;
	}
	
	/**
	 * Equals method
	 * @param o another timestamp instance
	 * @return true if the same, false otherwise
	 */
	public boolean equals(Object o) {
		Timestamp other = (Timestamp)o;
		if (this.toString().equals(other.toString()))
			return true;
		return false;
	}
	
}
