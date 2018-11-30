package edu.ncsu.csc316.security_log.manager;

import java.io.FileNotFoundException;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.data.Timestamp;
import edu.ncsu.csc316.security_log.dictionary.ArrayBasedList;
import edu.ncsu.csc316.security_log.dictionary.HashTable;
import edu.ncsu.csc316.security_log.io.LogEntryReader;

/**
 * The core class of securitylogmanager2
 * @author Ryan Alexander
 *
 */
public class SecurityLogManager {

	/** Holds an arraylist of the entries */
	private ArrayBasedList<LogEntry> rawEntries;
	/** Holds select entries */
	private HashTable<LogEntry> entries;
	
	/**
	 * Constructs a new SecurityLogManager given
	 * the path to the input user activity log file.
	 * 
	 * @param filePath - the path to the user activity log file
	 */
	public SecurityLogManager(String filePath)
	{
	    try {
			rawEntries = LogEntryReader.readEntries(filePath);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Invalid file name");
		}
	}

	/**
	 * Produces an operational profile of user activity
	 * performed between the given start and end dates (inclusive)
	 * 
	 * @param start - the start date in the format "MM/DD/YYYY HH:MM:SSXM"
	 * @param end - the end date in the format "MM/DD/YYYY HH:MM:SSXM"
	 * @return a string representing the operational profile
	 */
	public String generateOperationalProfile(String start, String end)
	{
		entries = new HashTable<LogEntry>();
		ArrayBasedList<LogEntry> actions = new ArrayBasedList<LogEntry>();
	    Timestamp first = new Timestamp(start);
	    Timestamp last = new Timestamp(end);
	    int total = 0;
	    StringBuilder operations = new StringBuilder(); 
	    operations.append("OperationalProfile[\n");
	    for (int i = 0; i < rawEntries.size(); i++) {
	    	if(rawEntries.get(i).getTimestamp().compareTo(first) == 1 && rawEntries.get(i).getTimestamp().compareTo(last) == -1) {
	    		LogEntry temp = entries.lookUp(rawEntries.get(i));
	    		if (temp == null) {
	    			entries.insert(rawEntries.get(i));
	    			temp = entries.lookUp(rawEntries.get(i));
	    		}
	    		if (temp.getFrequency() == 0) {
	    			actions.insert(temp);
	    		}
	    		temp.increaseFequency();
	    		total++;
	    	}
	    }
	    actions = profileMergeSort(actions);
	    for (int i = 0; i < actions.size(); i++) {
	    	LogEntry current = actions.get(i);
	    	double percent =  Math.round((double)current.getFrequency() / (double)total * 1000.0) / 10.0;
	    	operations.append(String.format("   %s: frequency: %d, percentage: %.1f%%\n", current.toString(), current.getFrequency(), percent));
	    }
	    operations.append("]");
	    //System.out.println(operations.toString());
		return operations.toString();
	}
	
	/**
	 * Produces a list of log entries for a given 
	 * user. The output list is sorted chronologically.
	 * 
	 * @param username - the user for which to generate a report
	 * @return a string representing the user report
	 */
	public String getUserReport(String username)
	{
	    ArrayBasedList<LogEntry> sorted = new ArrayBasedList<LogEntry>();
	    for(int i = 0; i < rawEntries.size(); i++) {
	    	if(rawEntries.get(i).getUser().equals(username)) {
	    		sorted.insert(rawEntries.get(i));
	    	}
	    }
	    sorted = mergeSort(sorted);
	    StringBuilder report = new StringBuilder();
	    report.append("Activity Report for " + username + "[\n");
	    for (int i = 0; i < sorted.size(); i++) {
	    	LogEntry temp = sorted.get(i);
	    	report.append(String.format("   %s - %s\n", temp.getTimestamp().toString(), temp.toString()));
	    }
	    if (sorted.isEmpty())
	    	report.append("   No activity was recorded.\n");
	    report.append("]");
		return report.toString();
	}
	
	/**
	 * Sorter for ordering output
	 * @param unsorted list of operations to be sorted
	 * @return unsorted sorted list
	 */
	public ArrayBasedList<LogEntry> mergeSort(ArrayBasedList<LogEntry> unsorted) {
		if(unsorted.size() > 1) {
			int mid = unsorted.size() / 2 - 1;
			ArrayBasedList<LogEntry> left = new ArrayBasedList<LogEntry>();
			ArrayBasedList<LogEntry> right = new ArrayBasedList<LogEntry>();
			//left
			for(int i = 0; i <= mid; i++) {
				left.add(i, unsorted.get(i));
			}
			//right
			for(int i = mid + 1; i < unsorted.size(); i++) {
				right.add(i - (mid + 1), unsorted.get(i));
			}
			left = mergeSort(left);
			right = mergeSort(right);
			unsorted = merge(unsorted, left, right);
		}
		return unsorted;
	}
	
	/**
	 * Helper method for mergesort
	 * @param sorted the main list
	 * @param left the left half to merge 
	 * @param right the right half to merge
	 * @return the new merged list
	 */
	public ArrayBasedList<LogEntry> merge(ArrayBasedList<LogEntry> sorted, ArrayBasedList<LogEntry> left, ArrayBasedList<LogEntry> right) {
		int l = 0;
		int r = 0;
		for(int i = 0; i < sorted.size(); i++) {
			if(r >= right.size() || (l < left.size() && left.get(l).getTimestamp().compareTo(right.get(r).getTimestamp()) == -1 )) {
				sorted.set(i, left.get(l));
				l += 1;
			} else if ((l < left.size() && left.get(l).getTimestamp().compareTo(right.get(r).getTimestamp()) == 0 ) && left.get(l).compareTo(right.get(r)) == -1) {
				sorted.set(i, left.get(l));
				l += 1;
			} else {
				sorted.set(i, right.get(r));
				r += 1;
			}
		}
		return sorted;
	}
	
	/**
	 * Sorter for ordering output from operationalProfile
	 * @param unsorted list of operations to be sorted
	 * @return unsorted sorted list
	 */
	public ArrayBasedList<LogEntry> profileMergeSort(ArrayBasedList<LogEntry> unsorted) {
		if(unsorted.size() > 1) {
			int mid = unsorted.size() / 2 - 1;
			ArrayBasedList<LogEntry> left = new ArrayBasedList<LogEntry>();
			ArrayBasedList<LogEntry> right = new ArrayBasedList<LogEntry>();
			//left 
			for(int i = 0; i <= mid; i++) {
				left.add(i, unsorted.get(i));
			}
			//right
			for(int i = mid + 1; i < unsorted.size(); i++) {
				right.add(i - (mid + 1), unsorted.get(i));
			}
			left = profileMergeSort(left);
			right = profileMergeSort(right);
			unsorted = pMerge(unsorted, left, right);
		}
		return unsorted;
	}

	/**
	 * Helper method for mergesort
	 * @param sorted the main list
	 * @param left the left half to merge 
	 * @param right the right half to merge
	 * @return the new merged list
	 */
	public ArrayBasedList<LogEntry> pMerge(ArrayBasedList<LogEntry> sorted, ArrayBasedList<LogEntry> left, ArrayBasedList<LogEntry> right) {
		int l = 0;
		int r = 0;
		for(int i = 0; i < sorted.size(); i++) {
			if (r >= right.size() || (l < left.size() && left.get(l).compareTo(right.get(r)) == -1)) {
				sorted.set(i, left.get(l));
				l += 1;
			} else {
				sorted.set(i, right.get(r));
				r += 1;
			}
		}
		return sorted;
	}
	
	/**
	public ArrayBasedList<LogEntry> insertionSort(ArrayBasedList<LogEntry> list) {
		for (int i = 1; i < list.size(); i++) {
			LogEntry temp = list.get(i);
			int j = i - 1;
			while (j >= 0 && temp.compareTo(list.get(j)) == -1) {
				list.set(j + 1, list.get(j));
				j--;
			}
			list.set(j + 1, temp);
		}
		return list;
	}
	*/
}
