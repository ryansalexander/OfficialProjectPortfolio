package edu.ncsu.csc316.security_log.data;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the log entry class
 * @author Ryan Alexander
 *
 */
public class LogEntryTest {

	/**
	 * Tests the entirety of the log entry class
	 */
	@Test
	public void testLogEntry() {
		LogEntry test = new LogEntry("jtking", "1/18/2018 1:22:21PM", "view", "prescription information");
		assertEquals("jtking", test.getUser());
		assertEquals("1/18/2018 1:22:21PM", test.getTimestamp().toString());
		assertEquals("view", test.getAction());
		assertEquals("prescription information", test.getResource());
		assertEquals(0, test.getFrequency());
		test.increaseFequency();
		assertEquals(1, test.getFrequency());
	}

}
