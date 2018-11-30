package edu.ncsu.csc316.security_log.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.dictionary.ArrayBasedList;


/**
 * Reads in the file from the user and constructs a list
 * Based on the provided file reader and loosely based on Project 3
 * @author Ryan Alexander
 *
 */
public class LogEntryReader {
	
	/** Line to be discarded from files */
	private static final String DISCARD = "USERNAME, TIMESTAMP, ACTION, RESOURCE";
	
	/**
	 * Reads in the entries from file
	 * @param filename the filepath
	 * @return logs a list of logentries
	 * @throws FileNotFoundException
	 */
	public static ArrayBasedList<LogEntry> readEntries(String filename) throws FileNotFoundException {
		ArrayBasedList<LogEntry> logs = new ArrayBasedList<LogEntry>();
		String user = "";
		String timestamp = "";
		String action = "";
		String resource = "";
		Scanner linescan = null;
		try(Scanner scan = new Scanner(new FileInputStream(filename), "UTF8"))
		{
		    while(scan.hasNextLine())
		    {
		    	String line = scan.nextLine().trim();
		    	if (line.isEmpty())
	        		break;
		        if(line.contains(DISCARD)) {
		        	line = scan.nextLine().trim();
		        }
		        
		        linescan = new Scanner(line);
		        while(linescan.hasNext()) {
		        	if(line.isEmpty())
			        	break;
		        	linescan.useDelimiter(",");
		        	if(linescan.hasNext())
		        		user = linescan.next().trim();
		        	else
		        		break;
		        	if(linescan.hasNext())
		        		timestamp = linescan.next().trim();
		        	else
		        		break;
		        	if(linescan.hasNext())
		        		action = linescan.next().trim();
		        	else
		        		break;
		        	if(linescan.hasNext()) {
		        		resource = linescan.next().trim();
		        		LogEntry entry = new LogEntry(user, timestamp, action, resource);
		        		logs.insert(entry);
		        	}
		        	break;
		        }
		        linescan.close();
		    }
		    scan.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("No Element");
		}
		return logs;
	}
}

