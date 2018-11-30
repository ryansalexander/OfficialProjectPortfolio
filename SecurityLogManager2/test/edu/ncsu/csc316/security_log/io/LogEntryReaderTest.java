package edu.ncsu.csc316.security_log.io;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import edu.ncsu.csc316.security_log.data.LogEntry;
import edu.ncsu.csc316.security_log.dictionary.ArrayBasedList;

/**
 * Tests the entirety of the log entry reader class
 * @author Ryan Alexander
 *
 */
public class LogEntryReaderTest {

	/**
	 * Tests the entirety of the log entry reader class
	 */
	@Test
	public void testReader() {
		try {
			LogEntryReader instance = new LogEntryReader();
			@SuppressWarnings("static-access")
			ArrayBasedList<LogEntry> test = instance.readEntries("input/activityLog_small.txt");
			assertEquals( "04/26/2017 12:33:15PM", test.get(0).getTimestamp().toString());
			assertEquals( "11/20/2016 02:07:54PM", test.get(15).getTimestamp().toString());
		} catch (FileNotFoundException e) {
			fail("Invalid file");
		}
	}

}
